package org.cdlib.ill.report;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import org.cdlib.ill.model.CampusReport;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("campuses", EnumSet.complementOf(EnumSet.of(VdxCampus.None)));
        model.addAttribute("reportStartDate", null);
        model.addAttribute("reportEndDate", null);
        return "report";
    }
    
    @PostMapping("/")
    public String query(ReportQuery query, Model model) {
        model.addAttribute("campuses", EnumSet.complementOf(EnumSet.of(VdxCampus.None)));
        model.addAttribute("reportStartDate", null);
        model.addAttribute("reportEndDate", null);
        return "report";
    }

}
