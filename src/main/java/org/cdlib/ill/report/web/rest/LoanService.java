package org.cdlib.ill.report.web.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.cdlib.ill.model.Loan;
import org.cdlib.ill.report.business.DataWarehouseRepository;
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
    DataWarehouseRepository repo;
    
    @GetMapping("/borrowing")
    public List<Loan> query() {
        return repo.getBorrowingHistory(LocalDate.MIN, LocalDate.MAX).collect(Collectors.toList());
    }
    
    @GetMapping("/{illno}")
    public ResponseEntity<Loan> query(@PathVariable Long illno) {
        return null;
    }

}
