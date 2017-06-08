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
@RequestMapping("/ill/data/by-campus/{campusCode}")
public class CampusDataWebService {

    @Autowired
    private DataWarehouseRepository repo;

    @RequestMapping(name = ".xml", produces = {"application/xml"})
    public HttpEntity<CampusReport> getCampusXml(
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return new ResponseEntity<>(repo.getCampusReport(campusCode, startDate, endDate), HttpStatus.OK);
    }

    @RequestMapping(name = ".json", produces = {"application/json"})
    public HttpEntity<CampusReport> getCampusJson(
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return new ResponseEntity<>(repo.getCampusReport(campusCode, startDate, endDate), HttpStatus.OK);
    }
    
    // and .csv
}
