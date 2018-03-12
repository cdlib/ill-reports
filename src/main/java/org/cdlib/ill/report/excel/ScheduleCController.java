package org.cdlib.ill.report.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingOCLC;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingOCLCRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUC;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUCRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLending;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Materials for Reporting...</a> document.
 *
 * @author mmorrisp
 */
@Controller
@RequestMapping("/ill/reports/by-campus/")
public class ScheduleCController {

    @Autowired
    private SpVdxBorrowingUCRepository spVdxBorrowingUCRepo;
    @Autowired
    private SpVdxBorrowingOCLCRepository spVdxBorrowingOCLCRepo;
    @Autowired
    private SpVdxLendingRepository spVdxLendingRepo;

    private final VdxCampus[] CAMPUSES_ORDERED = {
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
    };

    @RequestMapping(
            value = "{campusCode}/schedule_c.xlsx",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public void getBorrowingUC(
            @PathVariable("campusCode") String campusCode,
            OutputStream output,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();

        // Schedule C
        final XSSFSheet scheduleCSheet = wb.createSheet("Schedule C");
        scheduleCSheet.createRow(0).createCell(0).setCellValue("UC Libraries Statistics (" + startDate.toString() + " to " + endDate.toString() + ")");
        scheduleCSheet.createRow(1).createCell(0).setCellValue("SCHEDULE C: REPORT OF INTERLIBRARY TRANSACTIONS");
        scheduleCSheet.createRow(2);
        scheduleCSheet.getRow(2).createCell(0).setCellValue("Campus:");
        scheduleCSheet.getRow(2).createCell(1).setCellValue(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("all UC campuses"));
        scheduleCSheet.createRow(3);
        scheduleCSheet.getRow(3).createCell(0).setCellValue("Prepared by:");
        scheduleCSheet.getRow(3).createCell(1).setCellValue("California Digital Library (ILL Reports System)");
        int scheduleCCampusRow = 7;
        for (VdxCampus theirCampus : CAMPUSES_ORDERED) {
            String theirCampusCode = theirCampus.getCode();
            scheduleCSheet.createRow(scheduleCCampusRow);
            scheduleCSheet.getRow(scheduleCCampusRow).createCell(0).setCellValue(theirCampus.getDescription());
            scheduleCSheet.getRow(scheduleCCampusRow).createCell(1).setCellFormula("IFERROR(GETPIVOTDATA(\"Total\", 'Lending Rollup'!$A$1, \"Lending Campus\", \"" + theirCampusCode + "\", \"Loan Service\", \"Loan\"),0)");
            scheduleCSheet.getRow(scheduleCCampusRow).createCell(2).setCellFormula("IFERROR(GETPIVOTDATA(\"Total\", 'UC Borrowing Rollup'!$A$1, \"Borrowing Campus\", \"" + theirCampusCode + "\", \"Loan Service\", \"Loan\"),0)");
            scheduleCSheet.getRow(scheduleCCampusRow).createCell(3).setCellFormula("IFERROR(GETPIVOTDATA(\"Total\", 'Lending Rollup'!$A$1, \"Lending Campus\", \"" + theirCampusCode + "\", \"Loan Service\", \"Copy non returnable\"),0)");
            scheduleCSheet.getRow(scheduleCCampusRow).createCell(4).setCellFormula("IFERROR(GETPIVOTDATA(\"Total\", 'UC Borrowing Rollup'!$A$1, \"Borrowing Campus\", \"" + theirCampusCode + "\", \"Loan Service\", \"Copy non returnable\"),0)");
            scheduleCCampusRow++;
        }
        scheduleCSheet.createRow(19);
        scheduleCSheet.getRow(19).createCell(1).setCellFormula("SUM(B8:B19)");
        scheduleCSheet.getRow(19).createCell(2).setCellFormula("SUM(C8:C19)");
        scheduleCSheet.getRow(19).createCell(3).setCellFormula("SUM(D8:D19)");
        scheduleCSheet.getRow(19).createCell(4).setCellFormula("SUM(E8:E19)");
        scheduleCSheet.createRow(20);
        scheduleCSheet.getRow(20).createCell(1).setCellFormula("IFERROR(GETPIVOTDATA(\"Total\", 'Lending Rollup'!$A$1, \"Lending Campus\", \"\", \"Loan Service\", \"Loan\"),0)");
        scheduleCSheet.getRow(20).createCell(2).setCellFormula("IFERROR(GETPIVOTDATA(\"Total\", 'OCLC Borrowing Rollup'!$A$1, \"Loan Service\", \"Loan\"),0)");
        scheduleCSheet.getRow(20).createCell(3).setCellFormula("IFERROR(GETPIVOTDATA(\"Total\", 'Lending Rollup'!$A$1, \"Lending Campus\", \"\", \"Loan Service\", \"Copy non returnable\"),0)");
        scheduleCSheet.getRow(20).createCell(4).setCellFormula("IFERROR(GETPIVOTDATA(\"Total\", 'OCLC Borrowing Rollup'!$A$1, \"Loan Service\", \"Copy non returnable\"),0)");
        scheduleCSheet.createRow(21);
        scheduleCSheet.getRow(21).createCell(1).setCellFormula("SUM(B20:B21)");
        scheduleCSheet.getRow(21).createCell(2).setCellFormula("SUM(C20:C21)");
        scheduleCSheet.getRow(21).createCell(3).setCellFormula("SUM(D20:D21)");
        scheduleCSheet.getRow(21).createCell(4).setCellFormula("SUM(E20:E21)");

        // Adjustment sheet
        final XSSFSheet adjustmentSheet = wb.createSheet("Adjustments");
        
        // UC Borrowing data sheet
        final XSSFSheet ucBorrowingDataSheet = wb.createSheet("UC Borrowing");
        List<Field<SpVdxBorrowingUC>> ucBorrowingFields = Arrays.asList(
                Field.newField("Borrowing Campus", CellType.STRING, borrowing -> borrowing.getReqCampus().getCode()),
                Field.newField("Borrowing Library", CellType.STRING, borrowing -> borrowing.getReqName()),
                Field.newField("Lending Library", CellType.STRING, borrowing -> borrowing.getRespName()),
                Field.newField("Loan Service", CellType.STRING, borrowing -> borrowing.getServiceTp().getCode()),
                Field.newField("Delivery Method", CellType.STRING, borrowing -> borrowing.getShipDeliveryMethod().getCode()),
                Field.newField("Total", CellType.NUMERIC, borrowing -> borrowing.getCount())
        );
        int ucBorrowingRowCount = 0;
        int ucBorrowingCellCount = 0;
        Row ucBorrowingHeaderRow = ucBorrowingDataSheet.createRow(ucBorrowingRowCount++);
        for (Field<SpVdxBorrowingUC> field : ucBorrowingFields) {
            ucBorrowingHeaderRow.createCell(ucBorrowingCellCount++, field.getCellType()).setCellValue(field.getHeader());
        }
        for (SpVdxBorrowingUC record : spVdxBorrowingUCRepo.getBorrowingUC(
                VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
                startDate,
                endDate).collect(Collectors.toList())) {
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

        // UC Borrowing pivot sheet
        XSSFSheet ucBorrowingPivotSheet = wb.createSheet("UC Borrowing Rollup");
        AreaReference ucBorrowingSource = new AreaReference("'UC Borrowing'!A1:"
                + ReportWorkbookBuilder.getLastDataColumnLetter(ucBorrowingFields)
                + String.valueOf(ucBorrowingRowCount), SpreadsheetVersion.EXCEL2007);
        XSSFPivotTable ucBorrowingTable = ucBorrowingPivotSheet.createPivotTable(ucBorrowingSource, new CellReference("A1"));

        ucBorrowingTable.addRowLabel(0);
        ucBorrowingTable.addRowLabel(1);
        ucBorrowingTable.addRowLabel(2);
        ReportWorkbookBuilder.addColumnLabel(ucBorrowingTable, ucBorrowingSource, 3);
        ReportWorkbookBuilder.addColumnLabel(ucBorrowingTable, ucBorrowingSource, 4);
        ucBorrowingTable.addColumnLabel(DataConsolidateFunction.SUM, 5, "# Received");

        // OCLC Borrowing data sheet
        final XSSFSheet oclcBorrowingDataSheet = wb.createSheet("OCLC Borrowing");
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
                VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
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

        // OCLC Borrowing pivot sheet
        XSSFSheet oclcBorrowingPivotSheet = wb.createSheet("OCLC Borrowing Rollup");
        AreaReference oclcBorrowingSource = new AreaReference("'OCLC Borrowing'!A1:"
                + ReportWorkbookBuilder.getLastDataColumnLetter(oclcBorrowingFields)
                + String.valueOf(oclcBorrowingRowCount), SpreadsheetVersion.EXCEL2007);
        XSSFPivotTable oclcBorrowingTable = oclcBorrowingPivotSheet.createPivotTable(oclcBorrowingSource, new CellReference("A1"));

        oclcBorrowingTable.addRowLabel(0);
        oclcBorrowingTable.addRowLabel(1);
        oclcBorrowingTable.addRowLabel(2);
        ReportWorkbookBuilder.addColumnLabel(oclcBorrowingTable, oclcBorrowingSource, 3);
        oclcBorrowingTable.addColumnLabel(DataConsolidateFunction.SUM, 4, "# Received");

        // Lending data sheet
        final XSSFSheet lendingDataSheet = wb.createSheet("Lending");
        List<Field<SpVdxLending>> lendingFields = Arrays.asList(
                Field.newField("Lending Campus", CellType.STRING, lending -> lending.getRespCampus().getCode()),
                Field.newField("Lending Library", CellType.STRING, lending -> lending.getRespName()),
                Field.newField("Borrower's Location Type", CellType.STRING, lending -> lending.getReqLocType()),
                Field.newField("Borrowing Library", CellType.STRING, lending -> lending.getReqName()),
                Field.newField("Loan Service", CellType.STRING, lending -> lending.getServiceTp().getCode()),
                Field.newField("Delivery Method", CellType.STRING, lending -> lending.getShipDeliveryMethod().getCode()),
                Field.newField("Total", CellType.NUMERIC, lending -> lending.getCount())
        );
        int lendingRowCount = 0;
        int lendingCellCount = 0;
        Row lendingHeaderRow = lendingDataSheet.createRow(lendingRowCount++);
        for (Field<SpVdxLending> field : lendingFields) {
            lendingHeaderRow.createCell(lendingCellCount++, field.getCellType()).setCellValue(field.getHeader());
        }
        for (SpVdxLending record : spVdxLendingRepo.getLending(
                VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
                startDate,
                endDate).collect(Collectors.toList())) {
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

        // Lending pivot sheet
        XSSFSheet lendingPivotSheet = wb.createSheet("Lending Rollup");
        AreaReference lendingSource = new AreaReference("'Lending'!A1:"
                + ReportWorkbookBuilder.getLastDataColumnLetter(lendingFields)
                + String.valueOf(lendingRowCount), SpreadsheetVersion.EXCEL2007);
        XSSFPivotTable lendingTable = lendingPivotSheet.createPivotTable(lendingSource, new CellReference("A1"));

        lendingTable.addRowLabel(0);
        lendingTable.addRowLabel(1);
        lendingTable.addRowLabel(2);
        lendingTable.addRowLabel(3);
        ReportWorkbookBuilder.addColumnLabel(lendingTable, lendingSource, 4);
        ReportWorkbookBuilder.addColumnLabel(lendingTable, lendingSource, 5);
        lendingTable.addColumnLabel(DataConsolidateFunction.SUM, 6, "# Shipped");

        wb.write(output);
    }
}
