package org.cdlib.ill.report;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.EnumSet;
import org.cdlib.ill.model.CampusILLReport;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReportHtmlController {
    
    @Autowired
    private CampusILLReportService repo;
    
    private CampusILLReport getAllCampusReport(LocalDate reportStartDate, LocalDate reportEndDate) {
        return repo.getILLCampusReport("All Campuses", "all", "%", reportStartDate, reportEndDate);
    }
    
    private CampusILLReport getCampusReport(String campusCode, LocalDate reportStartDate, LocalDate reportEndDate) {
        return repo.getILLCampusReport(campusCode, reportStartDate, reportEndDate);
    }
    
    private LocalDate parseDate(String formatted) {
        return LocalDate.parse(formatted, DateTimeFormatter.ISO_LOCAL_DATE);
    }
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("campuses", EnumSet.complementOf(EnumSet.of(VdxCampus.None)));
        model.addAttribute("searchStartDate", null);
        model.addAttribute("searchEndDate", null);
        return "report";
    }
    
    @PostMapping("/")
    public String query(ReportHtmlForm query, Model model) {
        model.addAttribute("campuses", EnumSet.complementOf(EnumSet.of(VdxCampus.None)));
        model.addAttribute("campusDefault", VdxCampus.fromCode(query.getCampus()).map(VdxCampus::getCode).orElse("all"));
        model.addAttribute("searchStartDate", query.getFrom());
        model.addAttribute("searchEndDate", query.getTo());
        model.addAttribute("reports",
                Arrays.asList(VdxCampus.fromCode(query.getCampus()).isPresent()
                        ? getCampusReport(VdxCampus.fromCode(query.getCampus())
                                .filter((campus) -> campus != VdxCampus.None)
                                .map(VdxCampus::getCode)
                                .orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
                                parseDate(query.getFrom()),
                                parseDate(query.getTo()))
                        : getAllCampusReport(
                                parseDate(query.getFrom()),
                                parseDate(query.getTo()))
                ));
        return "report";
    }
    
}
