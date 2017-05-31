package org.cdlib.ill.report;

import org.cdlib.ill.model.CampusReport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mmorrisp
 */
@RestController
@RequestMapping("/ill-report/campus")
public class CampusReportWebService {
    
    @GetMapping("/{campus}")
    public CampusReport getCampus() {
        return null;
    }
    
}
