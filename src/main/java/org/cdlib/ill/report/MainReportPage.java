package org.cdlib.ill.report;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import org.cdlib.ill.model.CampusReport;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * HTML page with a form for querying campus ILL data.
 *
 * @author mmorrisp
 */
@Controller
public class MainReportPage {

    @Autowired
    private DataWarehouseRepository repo;

    private CampusReport getCampusData(String campus, LocalDate reportStartDate, LocalDate reportEndDate) {
        return repo.getCampusBorrowingSummary(campus, reportStartDate, reportEndDate);
    }

    private List<CampusReport> getCampusData(LocalDate reportStartDate, LocalDate reportEndDate) {
        return EnumSet.complementOf(EnumSet.of(VdxCampus.None)).stream()
                .map(VdxCampus::getCode)
                .map((String campus) -> {
                    return getCampusData(campus, reportStartDate, reportEndDate);
                })
                .collect(Collectors.toList());
    }

    @GetMapping()
    public String home(Model model,
            @RequestParam(name = "campus", required = false) String campus,
            @RequestParam(required = false, name = "reportStartDate", defaultValue = "2017-05-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reportStartDate,
            @RequestParam(required = false, name = "reportEndDate", defaultValue = "2017-06-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reportEndDate) {
        model.addAttribute("campuses", campus == null
                ? getCampusData(reportStartDate, reportEndDate)
                : Arrays.asList(getCampusData(campus, reportStartDate, reportEndDate)));
        model.addAttribute("reportStartDate", Date.valueOf(reportStartDate));
        model.addAttribute("reportEndDate", Date.valueOf(reportEndDate));
        return "report";
    }
    
    @PostMapping()
    public String query(ReportQuery query) {
        return "report";
    }

}
