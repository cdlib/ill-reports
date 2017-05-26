package org.cdlib.ill.report.web.rest;

import org.cdlib.ill.model.Loan;
import org.cdlib.ill.report.business.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mmorrisp
 */
@RestController
@RequestMapping("/ill")
public class LoanService {
    
    @Autowired
    ReportService reports;
    
    @GetMapping()
    public void test() {
        reports.test();
    }
    
    @GetMapping("/{illno}")
    public ResponseEntity<Loan> query(@PathVariable Long illno) {
        return null;
    }

}
