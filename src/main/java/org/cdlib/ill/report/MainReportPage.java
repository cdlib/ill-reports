package org.cdlib.ill.report;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import org.cdlib.ill.model.CampusReport;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * HTML home page with a form for loading & displaying campus ILL data.
 *
 * @author mmorrisp
 */
@Controller
public class MainReportPage {

    @Autowired
    private DataWarehouseRepository repo;

    private CampusReport getCampusData(String campus) {
        return repo.getCampusBorrowingSummary(campus, LocalDate.now().minusMonths(1), LocalDate.now());
    }

    private List<CampusReport> getCampusData() {
        return EnumSet.complementOf(EnumSet.of(VdxCampus.None)).stream()
                .map(VdxCampus::getCode)
                .map(this::getCampusData)
                .collect(Collectors.toList());
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam(name = "campus", required = false) String campus) {
        model.addAttribute("campuses", campus == null ? getCampusData() : Arrays.asList(getCampusData(campus)));
        return "report";
    }

}
