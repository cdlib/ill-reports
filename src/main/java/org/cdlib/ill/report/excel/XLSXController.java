package org.cdlib.ill.report.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.procedures.SpEtasEventRepository;
import org.cdlib.ill.report.vdx.procedures.SpEtasEvents;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingOCLC;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingOCLCRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingPatron;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingPatronRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingSummary;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingSummaryRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingTat;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingTatRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUC;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUCRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUnfilledSummary;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUnfilledSummaryRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxCopyright;
import org.cdlib.ill.report.vdx.procedures.SpVdxCopyrightRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxJournalBorrowing;
import org.cdlib.ill.report.vdx.procedures.SpVdxJournalBorrowingRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLending;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingPatron;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingPatronRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingSummary;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingSummaryRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingTat;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingTatRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingUnfilledSummary;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingUnfilledSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * File downloads intended for Microsoft Excel XLSX format.
 *
 * @author mmorrisp
 */
@Controller
@RequestMapping("/ill/reports/by-campus/")
public class XLSXController {

  @Autowired
  SpVdxBorrowingSummaryRepository spVdxBorrowingSummaryRepo;
  @Autowired
  SpVdxLendingSummaryRepository spVdxLendingSummaryRepo;
  @Autowired
  private SpVdxBorrowingUCRepository spVdxBorrowingUCRepo;
  @Autowired
  private SpVdxBorrowingOCLCRepository spVdxBorrowingOCLCRepo;
  @Autowired
  private SpVdxLendingRepository spVdxLendingRepo;
  @Autowired
  private SpVdxBorrowingPatronRepository spVdxBorrowingPatronRepo;
  @Autowired
  private SpVdxLendingPatronRepository spVdxLendingPatronRepo;
  @Autowired
  private SpVdxBorrowingTatRepository spVdxBorrowingTatRepo;
  @Autowired
  private SpVdxLendingTatRepository spVdxLendingTatRepo;
  @Autowired
  private SpVdxCopyrightRepository spVdxCopyrightRepo;
  @Autowired
  private SpVdxJournalBorrowingRepository spVdxJournalBorrowingRepo;
  @Autowired
  private SpVdxBorrowingUnfilledSummaryRepository spVdxBorrowingUnfilledSummaryRepo;
  @Autowired
  private SpVdxLendingUnfilledSummaryRepository spVdxLendingUnfilledSummaryRepo;
  @Autowired
  private SpEtasEventRepository etasEventRepo;

  @RequestMapping(
      value = "{campusCode}/borrowing_unfilled_summary_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getBorrowingUnfilledSummary(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxBorrowingUnfilledSummary.class)
        .fieldText("Borrowing Campus", summary -> summary.getReqCampus().getCode())
        .fieldText("Borrowing Library", summary -> summary.getReqName())
        .fieldText("Lending Library", summary -> summary.getRespName())
        .fieldText("Service Type", summary -> summary.getServiceTp().getCode())
        .fieldNum("Total", summary -> summary.getCount())
        .data(spVdxBorrowingUnfilledSummaryRepo.getBorrowingUnfilledSummary(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotColumn(3)
        .pivotValue(4, DataConsolidateFunction.SUM, "# of Unfilled Requests")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/lending_unfilled_summary_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getLendingUnfilledSummary(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxLendingUnfilledSummary.class)
        .fieldText("Lending Campus", summary -> summary.getRespCampus().getCode())
        .fieldText("Lending Library", SpVdxLendingUnfilledSummary::getRespName)
        .fieldText("Borrowing Library Type", SpVdxLendingUnfilledSummary::getReqLoctype)
        .fieldText("Borrowing Library", SpVdxLendingUnfilledSummary::getReqName)
        .fieldText("Service Type", summary -> summary.getServiceTp().getCode())
        .fieldNum("Total", summary -> summary.getCount())
        .data(spVdxLendingUnfilledSummaryRepo.getLendingUnfilledSummary(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotRow(2)
        .pivotRow(3)
        .pivotColumn(4)
        .pivotValue(5, DataConsolidateFunction.SUM, "# of Unfilled Responses")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/borrowing_summary_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getBorrowingSummary(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxBorrowingSummary.class)
        .fieldText("Borrowing Campus", summary -> summary.getReqCampus().getCode())
        .fieldText("Borrowing Library", summary -> summary.getReqName())
        .fieldText("Lender Category", summary -> summary.getRespCategory().getDescr())
        .fieldNum("Total", summary -> summary.getCount())
        .data(spVdxBorrowingSummaryRepo.getBorrowingSummary(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotColumn(2)
        .pivotValue(3, DataConsolidateFunction.SUM, "# of Requests")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/lending_summary_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getLendingSummary(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxLendingSummary.class)
        .fieldText("Lending Campus", summary -> summary.getRespCampus().getCode())
        .fieldText("Lending Library", summary -> summary.getRespName())
        .fieldText("Borrower Category", summary -> summary.getReqCategory().getDescr())
        .fieldNum("Total", summary -> summary.getCount())
        .data(spVdxLendingSummaryRepo.getLendingSummary(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotColumn(2)
        .pivotValue(3, DataConsolidateFunction.SUM, "# of Responses")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/borrowing_uc_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getBorrowingUC(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException{
    ReportWorkbookBuilder.newWorkbook(SpVdxBorrowingUC.class)
        .fieldText("Borrowing Campus", borrowing -> borrowing.getReqCampus().getCode())
        .fieldText("Borrowing Library", borrowing -> borrowing.getReqName())
        .fieldText("Lending Library", borrowing -> borrowing.getRespName())
        .fieldText("Service Type", borrowing -> borrowing.getServiceTp().getCode())
        .fieldText("Delivery Method", borrowing -> borrowing.getShipDeliveryMethod().getCode())
        .fieldNum("Total", borrowing -> borrowing.getCount())
        .data(spVdxBorrowingUCRepo.getBorrowingUC(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotRow(2)
        .pivotColumn(3)
        .pivotColumn(4)
        .pivotValue(5, DataConsolidateFunction.SUM, "# of Requests")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/borrowing_oclc_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getBorrowingOCLC(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxBorrowingOCLC.class)
        .fieldText("Borrowing Campus", borrowing -> borrowing.getReqCampus().getCode())
        .fieldText("Borrowing Library", borrowing -> borrowing.getReqName())
        .fieldText("Lending Library", borrowing -> borrowing.getRespName())
        .fieldText("Service Type", borrowing -> borrowing.getServiceTp().getCode())
        .fieldNum("Total", borrowing -> borrowing.getCount())
        .data(spVdxBorrowingOCLCRepo.getBorrowingOCLC(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotRow(2)
        .pivotColumn(3)
        .pivotValue(4, DataConsolidateFunction.SUM, "# of Requests")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/lending_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getLending(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxLending.class)
        .fieldText("Lending Campus", lending -> lending.getRespCampus().getCode())
        .fieldText("Lending Library", lending -> lending.getRespName())
        .fieldText("Borrowing Library", lending -> lending.getReqName())
        .fieldText("Borrower's Location Type", lending -> lending.getReqLocType())
        .fieldText("Service Type", lending -> lending.getServiceTp().getCode())
        .fieldText("Delivery Method", lending -> lending.getShipDeliveryMethod().getCode())
        .fieldNum("Total", lending -> lending.getCount())
        .data(spVdxLendingRepo.getLending(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotRow(3)
        .pivotRow(2)
        .pivotColumn(4)
        .pivotColumn(5)
        .pivotValue(6, DataConsolidateFunction.SUM, "# of Responses")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/borrowing_patron_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getBorrowingPatron(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxBorrowingPatron.class)
        .fieldText("Borrowing Campus", patron -> patron.getReqCampus().getCode())
        .fieldText("Borrowing Library", patron -> patron.getReqName())
        .fieldText("Lending Library", patron -> patron.getRespName())
        .fieldText("Loan Service", patron -> patron.getServiceTp().getCode())
        .fieldText("Patron Category", patron -> patron.getBorcat().getCode())
        .fieldNum("Total", patron -> patron.getCount())
        .data(spVdxBorrowingPatronRepo.getBorrowingPatron(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotRow(2)
        .pivotColumn(3)
        .pivotColumn(4)
        .pivotValue(5, DataConsolidateFunction.SUM, "# of Requests")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/lending_patron_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getLendingPatron(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxLendingPatron.class)
        .fieldText("Lending Campus", patron -> patron.getRespCampus().getCode())
        .fieldText("Lending Library", patron -> patron.getRespName())
        .fieldText("Borrowing Library", patron -> patron.getReqName())
        .fieldText("Loan Service", patron -> patron.getServiceTp().getCode())
        .fieldText("Patron Category", patron -> patron.getBorcat().getCode())
        .fieldNum("Total", patron -> patron.getCount())
        .data(spVdxLendingPatronRepo.getLendingPatron(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotRow(2)
        .pivotColumn(3)
        .pivotColumn(4)
        .pivotValue(5, DataConsolidateFunction.SUM, "# of Requests")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/borrowing_tat_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getBorrowingTat(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxBorrowingTat.class)
        .fieldText("Borrowing Campus", tat -> tat.getReqCampus().getCode())
        .fieldText("Borrowing Library", tat -> tat.getReqName())
        .fieldNum("Days", tat -> tat.getTat())
        .fieldText("Service Type", tat -> tat.getServiceTp().getCode())
        .fieldText("Delivery Method", tat -> tat.getShipDeliveryMethod().getCode())
        .fieldNum("Total", tat -> tat.getCount())
        .data(spVdxBorrowingTatRepo.getBorrowingTat(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotRow(2)
        .pivotColumn(3)
        .pivotColumn(4)
        .pivotValue(5, DataConsolidateFunction.SUM, "# of Requests per # of Days")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/lending_tat_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getLendingTat(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxLendingTat.class)
        .fieldText("Lending Campus", tat -> tat.getRespCampus().getCode())
        .fieldText("Lending Library", tat -> tat.getRespName())
        .fieldNum("Days", tat -> tat.getTat())
        .fieldText("Service Type", tat -> tat.getServiceTp().getCode())
        .fieldText("Delivery Method", tat -> tat.getShipDeliveryMethod().getCode())
        .fieldNum("Total", tat -> tat.getCount())
        .data(spVdxLendingTatRepo.getLendingTat(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotRow(2)
        .pivotColumn(3)
        .pivotColumn(4)
        .pivotValue(5, DataConsolidateFunction.SUM, "# of Responses per # of Days")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/copyright_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getCopyright(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxCopyright.class)
        .fieldText("Borrowing Campus", copy -> copy.getReqCampus().getCode())
        .fieldText("Requested Title", copy -> copy.getReqTitle())
        .fieldText("Publication Year", copy -> copy.getPubYear())
        .fieldNum("Total", copy -> copy.getCount())
        .data(spVdxCopyrightRepo.getCopyright(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotColumn(2)
        .pivotValue(3, DataConsolidateFunction.SUM, "# of Requests")
        .build()
        .write(output);
  }

  @RequestMapping(
      value = "{campusCode}/journal_borrowing_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getJournalBorrowing(
      @PathVariable("campusCode") String campusCode,
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpVdxJournalBorrowing.class)
        .fieldText("Borrowing Campus", borrowing -> borrowing.getReqCampus().getCode())
        .fieldText("Borrowing Library", borrowing -> borrowing.getReqName())
        .fieldText("Requested Title", borrowing -> borrowing.getReqTitle())
        .fieldText("Publication Year", borrowing -> borrowing.getPubYear())
        .fieldText("Volume / Issue", borrowing -> borrowing.getReqIssueTitle())
        .fieldText("Pagination", borrowing -> borrowing.getPagination())
        .fieldText("Patron Category", borrowing -> borrowing.getBorcat().getCode())
        .fieldNum("Total", borrowing -> borrowing.getCount())
        .data(spVdxJournalBorrowingRepo.getJournalBorrowing(
            VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"),
            startDate,
            endDate).collect(Collectors.toList()))
        .pivotRow(0)
        .pivotRow(1)
        .pivotRow(3)
        .pivotRow(2)
        .pivotColumn(6)
        .pivotValue(7, DataConsolidateFunction.SUM, "# of Requests per Publication Year")
        .build()
        .write(output);
  }
  
  @RequestMapping(
      value = "etas_data_{startDate}_{endDate}.xlsx",
      produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
  public void getEtas(
      OutputStream output,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    ReportWorkbookBuilder.newWorkbook(SpEtasEvents.class)
        .fieldText("Borrowing Campus", etas -> etas.getBorrowingCampus())
        .fieldNum("ETAS Links Presented", etas -> etas.getEtasLinkTotal())
        .fieldNum("ETAS Links Clicked", etas -> etas.getEtasClickTotal())
        .fieldNum("Total ETAS Requests Placed", etas -> etas.getEtasRequestNumber())
        .fieldNum("Returnables ETAS Requests Placed", etas -> etas.getReturnableRequestNumber())
        .fieldNum("Non-Returnables ETAS Requests Placed", etas -> etas.getNonreturnableRequestNumber())
        .fieldNum("Requests Not Placed", etas -> etas.getRequestNotPlacedNumber())
        .fieldNum("Total Requests Placed", etas -> etas.getTotalUCRequestsPlaced())
        .fieldNum("Total Requests Placed", etas -> etas.getTotalOCLCRequestsPlaced())
        .data(etasEventRepo.getEtasEvents(
            startDate,
            endDate).collect(Collectors.toList()))
        .build()
        .write(output);
  }
}
