package org.cdlib.ill.report;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.cdlib.ill.model.CampusILLReport;
import org.cdlib.ill.report.vdx.VdxBorrowingRepository;
import org.cdlib.ill.report.vdx.VdxBorrowingUC;
import org.cdlib.ill.report.vdx.VdxCampus;
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
 * A web service for precomputed ILL statistics.
 *
 * @author mmorrisp
 */
@RestController()
@RequestMapping("/ill/stats/by-campus/")
public class CampusILLStatisticsWebService {

    @Autowired
    private CampusILLReportService reportService;
    @Autowired
    private VdxBorrowingRepository vdxBorrowingRepo;

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

    @RequestMapping(value = "{campusCode}/borrowing_uc.csv", produces = {"text/csv"})
    public void getVdxBorrowingUCCsv(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(VdxBorrowingUC.class).withHeader();
        List<VdxBorrowingUC> data = vdxBorrowingRepo.getBorrowingUC(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse(""), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }
}
