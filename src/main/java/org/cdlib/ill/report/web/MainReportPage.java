package org.cdlib.ill.report.web;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.cdlib.ill.model.Campus;
import org.cdlib.ill.report.business.DataWarehouseRepository;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HTML home page with a form for loading & displaying campus ILL data.
 *
 * @author mmorrisp
 */
@Controller
public class MainReportPage {

    @Autowired
    private DataWarehouseRepository repo;
    
    private List<Campus> getCampusData() {
        return Stream.of(VdxCampus.values()).map(VdxCampus::getCode).map((String code) -> {
            return repo.getCampusBorrowingSummary(code, LocalDate.now().minusMonths(1), LocalDate.now());
        }).collect(Collectors.toList());
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("campuses", getCampusData());
        return "report";
    }

}
