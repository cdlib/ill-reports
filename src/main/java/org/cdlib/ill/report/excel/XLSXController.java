package org.cdlib.ill.report.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingOCLC;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingOCLCRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingTat;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingTatRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUC;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUCRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLending;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingTat;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingTatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * File downloads intended for Microsoft Excel.
 *
 * @author mmorrisp
 */
@Controller
@RequestMapping("/ill/reports/by-campus/")
public class XLSXController {

    @Autowired
    private SpVdxBorrowingUCRepository spVdxBorrowingUCRepo;
    @Autowired
    private SpVdxBorrowingOCLCRepository spVdxBorrowingOCLCRepo;
    @Autowired
    private SpVdxLendingRepository spVdxLendingRepo;
    @Autowired
    private SpVdxBorrowingTatRepository spVdxBorrowingTatRepo;
    @Autowired
    private SpVdxLendingTatRepository spVdxLendingTatRepo;

    @RequestMapping(
            value = "{campusCode}/borrowing_uc.xlsx",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public void getBorrowingUC(
            @PathVariable("campusCode") String campusCode,
            OutputStream output,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        ReportWorkbookBuilder.newWorkbook(SpVdxBorrowingUC.class)
                .fieldText("borrowing campus", borrowing -> borrowing.getReqCampus().getCode())
                .fieldText("borrowing library", borrowing -> borrowing.getReqName())
                .fieldText("lending library", borrowing -> borrowing.getRespName())
                .fieldText("service type", borrowing -> borrowing.getServiceTp().getCode())
                .fieldText("delivery method", borrowing -> borrowing.getShipDeliveryMethod().getCode())
                .fieldNum("total", borrowing -> borrowing.getCount())
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
            value = "{campusCode}/borrowing_oclc.xlsx",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public void getBorrowingOCLC(
            @PathVariable("campusCode") String campusCode,
            OutputStream output,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        ReportWorkbookBuilder.newWorkbook(SpVdxBorrowingOCLC.class)
                .fieldText("borrowing campus", borrowing -> borrowing.getReqCampus().getCode())
                .fieldText("borrowing library", borrowing -> borrowing.getReqName())
                .fieldText("lending library", borrowing -> borrowing.getRespName())
                .fieldText("service type", borrowing -> borrowing.getServiceTp().getCode())
                .fieldNum("total", borrowing -> borrowing.getCount())
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
            value = "{campusCode}/lending.xlsx",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public void getLending(
            @PathVariable("campusCode") String campusCode,
            OutputStream output,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        ReportWorkbookBuilder.newWorkbook(SpVdxLending.class)
                .fieldText("lending campus", lending -> lending.getRespCampus().getCode())
                .fieldText("lending library", lending -> lending.getRespName())
                .fieldText("borrowing library", lending -> lending.getReqName())
                .fieldText("borrower's location type", lending -> lending.getReqLocType())
                .fieldText("service type", lending -> lending.getServiceTp().getCode())
                .fieldText("delivery method", lending -> lending.getShipDeliveryMethod().getCode())
                .fieldNum("total", lending -> lending.getCount())
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
            value = "{campusCode}/borrowing_tat.xlsx",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public void getBorrowingTat(
            @PathVariable("campusCode") String campusCode,
            OutputStream output,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        ReportWorkbookBuilder.newWorkbook(SpVdxBorrowingTat.class)
                .fieldText("borrowing campus", tat -> tat.getReqCampus().getCode())
                .fieldText("borrowing library", tat -> tat.getReqName())
                .fieldNum("days", tat -> tat.getTat())
                .fieldText("service type", tat -> tat.getServiceTp().getCode())
                .fieldText("delivery method", tat -> tat.getShipDeliveryMethod().getCode())
                .fieldNum("total", tat -> tat.getCount())
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
            value = "{campusCode}/lending_tat.xlsx",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public void getLendingTat(
            @PathVariable("campusCode") String campusCode,
            OutputStream output,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        ReportWorkbookBuilder.newWorkbook(SpVdxLendingTat.class)
                .fieldText("lending campus", tat -> tat.getRespCampus().getCode())
                .fieldText("lending library", tat -> tat.getRespName())
                .fieldNum("days", tat -> tat.getTat())
                .fieldText("service type", tat -> tat.getServiceTp().getCode())
                .fieldText("delivery method", tat -> tat.getShipDeliveryMethod().getCode())
                .fieldNum("total", tat -> tat.getCount())
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
}
