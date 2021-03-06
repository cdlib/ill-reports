package org.cdlib.ill.report.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingOCLC;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingOCLCRepository;
import org.cdlib.ill.report.vdx.schedule_c.LendingSubtotal;
import org.cdlib.ill.report.vdx.schedule_c.ScheduleCRepository;
import org.cdlib.ill.report.vdx.schedule_c.UCBorrowingSubtotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A detailed workbook to assist campus ILL staff filling out the Schedule C
 * report required by UCOP.
 *
 * See the
 * <a href="https://libraries.universityofcalifornia.edu/content/administrative-materials-reporting-university-california-library-statistics-2016-17-data">Administrative
 * Materials for Reporting</a> document.
 */
@Controller
@RequestMapping("/ill/reports/by-campus/")
public class ScheduleCController {

  @Autowired
  private ScheduleCRepository scheduleCRepo;
  @Autowired
  private SpVdxBorrowingOCLCRepository spVdxBorrowingOCLCRepo;

  private static final ClassPathResource TEMPLATE = new ClassPathResource("org/cdlib/ill/report/ScheduleC_Template.xlsx");

  private static final Supplier<IllegalArgumentException> NO_SUCH_CAMPUS = () -> {
    return new NoSuchCampusException("Schedule C is only available for individual campuses. Please choose a campus.");
  };

  @RequestMapping(
      value = "{campusCode}/schedule_c_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getScheduleC(
      @PathVariable("campusCode") String campusCode,
      OutputStream clientDownload,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {

    // A specific campus is required.
    VdxCampus campus = VdxCampus.fromCode(campusCode).orElseThrow(NO_SUCH_CAMPUS);

    try (XSSFWorkbook wb = new XSSFWorkbook(TEMPLATE.getInputStream())) {
      buildScheduleCSheet(wb, campus, startDate, endDate);
      buildUCBorrowingSheets(wb, campus, startDate, endDate);
      buildOCLCBorrowingSheets(wb, campus, startDate, endDate);
      buildLendingSheets(wb, campus, startDate, endDate);
      wb.write(clientDownload);
    }
  }

  // The order that the campuses appear in the XLSX template file.
  private static final List<VdxCampus> CAMPUS_ORDER = Arrays.asList(
      VdxCampus.Berkeley,
      VdxCampus.Davis,
      VdxCampus.Irvine,
      VdxCampus.LosAngeles,
      VdxCampus.Merced,
      VdxCampus.Riverside,
      VdxCampus.SanDiego,
      VdxCampus.SanFrancisco,
      VdxCampus.SantaBarbara,
      VdxCampus.SantaCruz,
      VdxCampus.NorthernRegionalLibraryFacility,
      VdxCampus.SouthernRegionalLibraryFacility
  );

  private static final Set<VdxCampus> RLFS = EnumSet.of(VdxCampus.NorthernRegionalLibraryFacility, VdxCampus.SouthernRegionalLibraryFacility);

  private void buildScheduleCSheet(XSSFWorkbook wb, VdxCampus campus, LocalDate startDate, LocalDate endDate) {
    final XSSFSheet scheduleCSheet = wb.getSheet("Schedule C");
    scheduleCSheet.createRow(0).createCell(0).setCellValue("UC Libraries Statistics (" + startDate.toString() + " to " + endDate.toString() + ")");
    scheduleCSheet.getRow(2).createCell(1).setCellValue(campus.getDescription());

    // Assumption that the "UC Libraries" row is grayed out.
    XSSFCellStyle disabledStyle = scheduleCSheet.getRow(6).getCell(1).getCellStyle();

    // Clear the home campus values on "Schedule C"
    XSSFRow disabledRow1 = scheduleCSheet.getRow(7 + CAMPUS_ORDER.indexOf(campus));
    for (short index = 1; index < disabledRow1.getLastCellNum(); index++) {
      if (!RLFS.contains(campus) || index % 2 == 0) {
        XSSFCell cell = disabledRow1.getCell(index);
        if (cell.getCellTypeEnum() == CellType.FORMULA) {
          cell.setCellFormula(null);
          cell.setCellValue("");
          cell.setCellType(CellType.BLANK);
          cell.setCellStyle(disabledStyle);
        }
      }
    }

    final XSSFSheet adjustmentsSheet = wb.getSheet("Adjustments");

    // Clear the home campus values on "Adjustments"
    XSSFRow disabledRow2 = adjustmentsSheet.getRow(7 + CAMPUS_ORDER.indexOf(campus));
    for (short index = 1; index < disabledRow2.getLastCellNum(); index++) {
      if (!RLFS.contains(campus) || index % 2 == 0) {
        XSSFCell cell = disabledRow2.getCell(index);
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
          cell.setCellValue("");
          cell.setCellType(CellType.BLANK);
          cell.setCellStyle(disabledStyle);
        }
      }
    }

  }

  private void buildUCBorrowingSheets(XSSFWorkbook wb, VdxCampus campus, LocalDate startDate, LocalDate endDate) {
    // Data sheet
    final XSSFSheet ucBorrowingDataSheet = wb.getSheet("UC Borrowing");
    List<Field<UCBorrowingSubtotal>> ucBorrowingFields = Arrays.asList(
        Field.newField("Borrowing Campus", CellType.STRING, borrowing -> borrowing.getReqCampus().getCode()),
        Field.newField("Borrowing Library", CellType.STRING, borrowing -> borrowing.getReqName()),
        Field.newField("Lending Campus", CellType.STRING, borrowing -> borrowing.getRespCampus().getCode()),
        Field.newField("Lending Library", CellType.STRING, borrowing -> borrowing.getRespName()),
        Field.newField("Loan Service", CellType.STRING, borrowing -> borrowing.getServiceTp().getCode()),
        Field.newField("Delivery Method", CellType.STRING, borrowing -> borrowing.getShipDeliveryMethod().getCode()),
        Field.newField("Total", CellType.NUMERIC, borrowing -> borrowing.getCount())
    );
    int ucBorrowingRowCount = 0;
    int ucBorrowingCellCount = 0;
    Row ucBorrowingHeaderRow = ucBorrowingDataSheet.createRow(ucBorrowingRowCount++);
    for (Field<UCBorrowingSubtotal> field : ucBorrowingFields) {
      ucBorrowingHeaderRow.createCell(ucBorrowingCellCount++, field.getCellType()).setCellValue(field.getHeader());
    }
    for (UCBorrowingSubtotal record : scheduleCRepo.getBorrowing(
        campus.getCode(),
        startDate,
        endDate)) {
      Row dataRow = ucBorrowingDataSheet.createRow(ucBorrowingRowCount++);
      for (int cell = 0; cell < ucBorrowingFields.size(); cell++) {
        switch (ucBorrowingFields.get(cell).getCellType()) {
          case NUMERIC:
            dataRow.createCell(cell, ucBorrowingFields.get(cell).getCellType())
                .setCellValue(Double.parseDouble(String.valueOf(ucBorrowingFields.get(cell).getMapper().apply(record))));
            break;
          default:
            dataRow.createCell(cell, ucBorrowingFields.get(cell).getCellType())
                .setCellValue(String.valueOf(ucBorrowingFields.get(cell).getMapper().apply(record)));
            break;
        }
      }
    }

    // Pivot table sheet
    final XSSFSheet ucBorrowingPivotSheet = wb.getSheet("UC Borrowing Rollup");
    AreaReference ucBorrowingSource = new AreaReference("'UC Borrowing'!A1:"
        + ReportWorkbookBuilder.getLastDataColumnLetter(ucBorrowingFields)
        + String.valueOf(ucBorrowingRowCount), SpreadsheetVersion.EXCEL2007);
    XSSFPivotTable ucBorrowingTable = ucBorrowingPivotSheet.createPivotTable(ucBorrowingSource, new CellReference("A1"));
    ucBorrowingTable.addRowLabel(2);
    ucBorrowingTable.addRowLabel(3);
    ReportWorkbookBuilder.addColumnLabel(ucBorrowingTable, ucBorrowingSource, 4);
    ucBorrowingTable.addColumnLabel(DataConsolidateFunction.SUM, 6, "# Received");
  }

  private void buildOCLCBorrowingSheets(XSSFWorkbook wb, VdxCampus campus, LocalDate startDate, LocalDate endDate) {
    // Data sheet
    final XSSFSheet oclcBorrowingDataSheet = wb.getSheet("OCLC Borrowing");
    List<Field<SpVdxBorrowingOCLC>> oclcBorrowingFields = Arrays.asList(
        Field.newField("Borrowing Campus", CellType.STRING, borrowing -> borrowing.getReqCampus().getCode()),
        Field.newField("Borrowing Library", CellType.STRING, borrowing -> borrowing.getReqName()),
        Field.newField("Lending Library", CellType.STRING, borrowing -> borrowing.getRespName()),
        Field.newField("Loan Service", CellType.STRING, borrowing -> borrowing.getServiceTp().getCode()),
        Field.newField("Total", CellType.NUMERIC, borrowing -> borrowing.getCount())
    );
    int oclcBorrowingRowCount = 0;
    int oclcBorrowingCellCount = 0;
    Row oclcBorrowingHeaderRow = oclcBorrowingDataSheet.createRow(oclcBorrowingRowCount++);
    for (Field<SpVdxBorrowingOCLC> field : oclcBorrowingFields) {
      oclcBorrowingHeaderRow.createCell(oclcBorrowingCellCount++, field.getCellType()).setCellValue(field.getHeader());
    }
    for (SpVdxBorrowingOCLC record : spVdxBorrowingOCLCRepo.getBorrowingOCLC(
        campus.getCode(),
        startDate,
        endDate).collect(Collectors.toList())) {
      Row dataRow = oclcBorrowingDataSheet.createRow(oclcBorrowingRowCount++);
      for (int cell = 0; cell < oclcBorrowingFields.size(); cell++) {
        switch (oclcBorrowingFields.get(cell).getCellType()) {
          case NUMERIC:
            dataRow.createCell(cell, oclcBorrowingFields.get(cell).getCellType())
                .setCellValue(Double.parseDouble(String.valueOf(oclcBorrowingFields.get(cell).getMapper().apply(record))));
            break;
          default:
            dataRow.createCell(cell, oclcBorrowingFields.get(cell).getCellType())
                .setCellValue(String.valueOf(oclcBorrowingFields.get(cell).getMapper().apply(record)));
            break;
        }
      }
    }

    // Pivot table sheet
    XSSFSheet oclcBorrowingPivotSheet = wb.getSheet("OCLC Borrowing Rollup");
    AreaReference oclcBorrowingSource = new AreaReference("'OCLC Borrowing'!A1:"
        + ReportWorkbookBuilder.getLastDataColumnLetter(oclcBorrowingFields)
        + String.valueOf(oclcBorrowingRowCount), SpreadsheetVersion.EXCEL2007);
    XSSFPivotTable oclcBorrowingTable = oclcBorrowingPivotSheet.createPivotTable(oclcBorrowingSource, new CellReference("A1"));
    oclcBorrowingTable.addRowLabel(2);
    ReportWorkbookBuilder.addColumnLabel(oclcBorrowingTable, oclcBorrowingSource, 3);
    oclcBorrowingTable.addColumnLabel(DataConsolidateFunction.SUM, 4, "# Received");
  }

  private void buildLendingSheets(XSSFWorkbook wb, VdxCampus campus, LocalDate startDate, LocalDate endDate) {
    // Data sheet
    final XSSFSheet lendingDataSheet = wb.getSheet("Lending");
    List<Field<LendingSubtotal>> lendingFields = Arrays.asList(
        Field.newField("Lending Campus", CellType.STRING, lending -> lending.getRespCampus().getCode()),
        Field.newField("Lending Library", CellType.STRING, lending -> lending.getRespName()),
        Field.newField("Borrowing Location Type", CellType.STRING, lending -> lending.getReqLocType()),
        Field.newField("Borrowing Campus", CellType.STRING, lending -> lending.getReqCampus().getCode()),
        Field.newField("Borrowing Library", CellType.STRING, lending -> lending.getReqName()),
        Field.newField("Loan Service", CellType.STRING, lending -> lending.getServiceTp().getCode()),
        Field.newField("Delivery Method", CellType.STRING, lending -> lending.getShipDeliveryMethod().getCode()),
        Field.newField("Total", CellType.NUMERIC, lending -> lending.getCount())
    );
    int lendingRowCount = 0;
    int lendingCellCount = 0;
    Row lendingHeaderRow = lendingDataSheet.createRow(lendingRowCount++);
    for (Field<LendingSubtotal> field : lendingFields) {
      lendingHeaderRow.createCell(lendingCellCount++, field.getCellType()).setCellValue(field.getHeader());
    }
    for (LendingSubtotal record : scheduleCRepo.getLending(
        campus.getCode(),
        startDate,
        endDate)) {
      Row dataRow = lendingDataSheet.createRow(lendingRowCount++);
      for (int cell = 0; cell < lendingFields.size(); cell++) {
        switch (lendingFields.get(cell).getCellType()) {
          case NUMERIC:
            dataRow.createCell(cell, lendingFields.get(cell).getCellType())
                .setCellValue(Double.parseDouble(String.valueOf(lendingFields.get(cell).getMapper().apply(record))));
            break;
          default:
            dataRow.createCell(cell, lendingFields.get(cell).getCellType())
                .setCellValue(String.valueOf(lendingFields.get(cell).getMapper().apply(record)));
            break;
        }
      }
    }

    // Pivot table sheet
    final XSSFSheet lendingPivotSheet = wb.getSheet("Lending Rollup");
    AreaReference lendingSource = new AreaReference("'Lending'!A1:"
        + ReportWorkbookBuilder.getLastDataColumnLetter(lendingFields)
        + String.valueOf(lendingRowCount), SpreadsheetVersion.EXCEL2007);
    XSSFPivotTable lendingTable = lendingPivotSheet.createPivotTable(lendingSource, new CellReference("A1"));
    lendingTable.addRowLabel(3);
    lendingTable.addRowLabel(4);
    ReportWorkbookBuilder.addColumnLabel(lendingTable, lendingSource, 5);
    lendingTable.addColumnLabel(DataConsolidateFunction.SUM, 7, "# Shipped");
  }
}
