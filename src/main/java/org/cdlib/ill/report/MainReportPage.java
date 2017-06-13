package org.cdlib.ill.report;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import org.cdlib.ill.model.CampusReport;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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

    private CampusReport getCampusReport(String campus, LocalDate reportStartDate, LocalDate reportEndDate) {
        return repo.getCampusReport(campus, reportStartDate, reportEndDate);
    }

    private List<CampusReport> getCampusReports(LocalDate reportStartDate, LocalDate reportEndDate) {
        return EnumSet.complementOf(EnumSet.of(VdxCampus.None)).stream()
                .map(VdxCampus::getCode)
                .map((String campus) -> {
                    return getCampusReport(campus, reportStartDate, reportEndDate);
                })
                .collect(Collectors.toList());
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
    public String query(ReportQuery query, Model model) {
        if ("Reset".equalsIgnoreCase(query.getCommand())) {
            return home(model);
        }
        model.addAttribute("campuses", EnumSet.complementOf(EnumSet.of(VdxCampus.None)));
        model.addAttribute("campusDefault", VdxCampus.fromCode(query.getCampus()).map(VdxCampus::getCode).orElse(""));
        model.addAttribute("searchStartDate", query.getFrom());
        model.addAttribute("searchEndDate", query.getTo());
        model.addAttribute("reports", StringUtils.isEmpty(query.getCampus())
                ? getCampusReports(parseDate(query.getFrom()), parseDate(query.getTo()))
                : Arrays.asList(getCampusReport(query.getCampus(), parseDate(query.getFrom()), parseDate(query.getTo()))));
        return "report";
    }

}
