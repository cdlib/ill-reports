package org.cdlib.ill.report;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.List;
import org.cdlib.ill.model.CampusILLReport;
import org.cdlib.ill.report.vdx.VdxBorrowing;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxLending;
import org.cdlib.ill.report.vdx.VdxBorrowingRepository;
import org.cdlib.ill.report.vdx.VdxLendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/ill/data/by-campus/")
public class CampusILLDataWebService {

    @Autowired
    private CampusILLReportService reportService;
    @Autowired
    private VdxBorrowingRepository vdxBorrowingRepo;
    @Autowired
    private VdxLendingRepository vdxLendingRepo;

    @RequestMapping(value = "{campusCode}.xml", produces = {"application/xml"})
    public HttpEntity<CampusILLReport> getCampusXml(
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return new ResponseEntity<>(reportService.getILLCampusReport(campusCode, startDate, endDate), HttpStatus.OK);
    }

    @RequestMapping(value = "{campusCode}.json", produces = {"application/json"})
    public HttpEntity<CampusILLReport> getCampusJson(
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return new ResponseEntity<>(reportService.getILLCampusReport(campusCode, startDate, endDate), HttpStatus.OK);
    }

    @RequestMapping(value = "{campusCode}/borrowing.xml", produces = {"application/xml"})
    public HttpEntity<List<VdxBorrowing>> getCampusBorrowingXml(@PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return new ResponseEntity<>(vdxBorrowingRepo.getVdxBorrowing(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse(""), startDate, endDate), HttpStatus.OK);
    }

    @RequestMapping(value = "{campusCode}/borrowing.json", produces = {"application/json"})
    public HttpEntity<List<VdxBorrowing>> getCampusBorrowingJson(@PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return new ResponseEntity<>(vdxBorrowingRepo.getVdxBorrowing(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse(""), startDate, endDate), HttpStatus.OK);
    }

    @RequestMapping(value = "{campusCode}/borrowing.csv", produces = {"text/csv"})
    public void getCampusBorrowingCsv(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(VdxBorrowing.class).withHeader();
        List<VdxBorrowing> data = vdxBorrowingRepo.getVdxBorrowing(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse(""), startDate, endDate);
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/lending.xml", produces = {"application/xml"})
    public HttpEntity<List<VdxLending>> getCampusLendingXml(@PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return new ResponseEntity<>(vdxLendingRepo.getVdxLending(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse(""), startDate, endDate), HttpStatus.OK);
    }

    @RequestMapping(value = "{campusCode}/lending.json", produces = {"application/json"})
    public HttpEntity<List<VdxLending>> getCampusLendingJson(@PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return new ResponseEntity<>(vdxLendingRepo.getVdxLending(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse(""), startDate, endDate), HttpStatus.OK);
    }

    @RequestMapping(value = "{campusCode}/lending.csv", produces = {"text/csv"})
    public void getCampusLendingCsv(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(VdxLending.class).withHeader();
        List<VdxLending> data = vdxLendingRepo.getVdxLending(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse(""), startDate, endDate);
        mapper.writer(schema).writeValue(output, data);
    }

}
