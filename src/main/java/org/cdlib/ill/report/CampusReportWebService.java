package org.cdlib.ill.report;

import java.time.LocalDate;
import org.cdlib.ill.model.CampusReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mmorrisp
 */
@RestController
@RequestMapping("/ill-report/campus")
public class CampusReportWebService {
    
    @Autowired
    private DataWarehouseRepository repo;
    
    @GetMapping("/{campus}")
    public CampusReport getCampus(@PathVariable("campus") String campusCode) {
        return repo.getCampusBorrowingSummary(campusCode, LocalDate.now().minusMonths(1), LocalDate.now());
    }
    
}
