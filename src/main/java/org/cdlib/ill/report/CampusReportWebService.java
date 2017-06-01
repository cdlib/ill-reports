package org.cdlib.ill.report;

import java.time.LocalDate;
import org.cdlib.ill.model.CampusReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mmorrisp
 */
@RestController()
@RequestMapping("/ill/report/by-campus")
public class CampusReportWebService {

    @Autowired
    private DataWarehouseRepository repo;

    @RequestMapping("/{campusCode}")
    public HttpEntity<CampusReport> getCampus(
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "reportStartDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reportStartDate,
            @RequestParam(required = false, name = "reportEndDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reportEndDate) {
        return new ResponseEntity<CampusReport>(repo.getCampusBorrowingSummary(campusCode, reportStartDate, reportEndDate), HttpStatus.OK);
    }

}
