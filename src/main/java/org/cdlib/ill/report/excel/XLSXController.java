package org.cdlib.ill.report.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingTat;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingTatRepository;
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
    private SpVdxBorrowingTatRepository spVdxBorrowingTatRepo;
    @Autowired
    private SpVdxLendingTatRepository spVdxLendingTatRepo;

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
