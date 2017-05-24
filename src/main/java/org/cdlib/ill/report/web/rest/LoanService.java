package org.cdlib.ill.report.web.rest;

import org.cdlib.ill.model.Loan;
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
    
    @GetMapping("/{illno}")
    public ResponseEntity<Loan> query(@PathVariable Long illno) {
        return null;
    }

}
