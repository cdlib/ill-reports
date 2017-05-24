package org.cdlib.ill.report.web;

import java.time.LocalDate;
import org.cdlib.ill.model.Loan;
import org.cdlib.ill.report.business.DataWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Formerly, the VDX Borrowing Summary Report.
 * 
 * @author mmorrisp
 */
@Controller
@RequestMapping("/report/borrowing-by-lendor-category")
public class BorrowingByLendorCategory {
    @Autowired
    DataWarehouseRepository repo;
    
    @GetMapping(path = ".csv")
    public ModelAndView makeTheReport(@RequestParam("start") LocalDate start, @RequestParam("end") LocalDate end) {
        repo.doBatchOperation(start, end, (Loan l) -> {
            
        });
        return new ModelAndView();
    }
}
