package org.cdlib.ill.report.api;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.cdlib.ill.report.Constants;
import org.cdlib.ill.report.vdx.VdxBorrowing;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxLending;
import org.cdlib.ill.report.vdx.VdxBorrowingRepository;
import org.cdlib.ill.report.vdx.VdxLendingRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingBilling;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingBillingRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingUnfilledDetail;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingUnfilledDetailRepository;
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
 * A web service for ILL data. UC ILL data are derived from VDX data.
 *
 * TODO: Throw an exception => 400 when the campus is not an enumerated value &
 * unit test.
 *
 * @author mmorrisp
 */
@RestController()
@RequestMapping("/ill/data/by-campus/")
public class CampusILLDataRestController {

    @Autowired
    private VdxBorrowingRepository vdxBorrowingRepo;
    @Autowired
    private VdxLendingRepository vdxLendingRepo;
    @Autowired
    private SpVdxLendingUnfilledDetailRepository vdxLendingUnfilledRepo;
    @Autowired
    private SpVdxLendingBillingRepository vdxLendingBillingRepo;

    /**
     *
     * @param campusCode
     * @param startDate
     * @param endDate
     * @return
     * @throws IllegalArgumentException if {@code campusCode} is not valid.
     */
    @RequestMapping(value = "{campusCode}/borrowing.xml", produces = {"application/xml"})
    public HttpEntity<List<VdxBorrowing>> getCampusBorrowingXml(@PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate)
            throws IllegalArgumentException {
        return new ResponseEntity<>(vdxBorrowingRepo.getVdxBorrowing(VdxCampus.fromCode(campusCode).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER), startDate, endDate), HttpStatus.OK);
    }

    /**
     *
     * @param campusCode
     * @param startDate
     * @param endDate
     * @return
     * @throws IllegalArgumentException if {@code campusCode} is not valid.
     */
    @RequestMapping(value = "{campusCode}/borrowing.json", produces = {"application/json"})
    public HttpEntity<List<VdxBorrowing>> getCampusBorrowingJson(@PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate)
            throws IllegalArgumentException {
        return new ResponseEntity<>(vdxBorrowingRepo.getVdxBorrowing(VdxCampus.fromCode(campusCode).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER), startDate, endDate), HttpStatus.OK);
    }

    /**
     *
     * @param output
     * @param campusCode
     * @param startDate
     * @param endDate
     * @throws IllegalArgumentException if {@code campusCode} is not valid.
     * @throws IOException
     */
    @RequestMapping(value = "{campusCode}/borrowing.csv", produces = {"text/csv"})
    public void getCampusBorrowingCsv(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate)
            throws IllegalArgumentException, IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(VdxBorrowing.class).withHeader();
        List<VdxBorrowing> data = vdxBorrowingRepo.getVdxBorrowing(VdxCampus.fromCode(campusCode).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER), startDate, endDate);
        mapper.writer(schema).writeValue(output, data);
    }

    /**
     *
     * @param campusCode
     * @param startDate
     * @param endDate
     * @return
     * @throws IllegalArgumentException if {@code campusCode} is not valid.
     */
    @RequestMapping(value = "{campusCode}/lending.xml", produces = {"application/xml"})
    public HttpEntity<List<VdxLending>> getCampusLendingXml(@PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate)
            throws IllegalArgumentException {
        return new ResponseEntity<>(vdxLendingRepo.getVdxLending(VdxCampus.fromCode(campusCode).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER), startDate, endDate), HttpStatus.OK);
    }

    /**
     *
     * @param campusCode
     * @param startDate
     * @param endDate
     * @return
     * @throws IllegalArgumentException if {@code campusCode} is not valid.
     */
    @RequestMapping(value = "{campusCode}/lending.json", produces = {"application/json"})
    public HttpEntity<List<VdxLending>> getCampusLendingJson(@PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate)
            throws IllegalArgumentException {
        return new ResponseEntity<>(vdxLendingRepo.getVdxLending(VdxCampus.fromCode(campusCode).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER), startDate, endDate), HttpStatus.OK);
    }

    /**
     *
     * @param output
     * @param campusCode
     * @param startDate
     * @param endDate
     * @throws IllegalArgumentException if {@code campusCode} is not valid.
     * @throws IOException
     */
    @RequestMapping(value = "{campusCode}/lending.csv", produces = {"text/csv"})
    public void getCampusLendingCsv(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate)
            throws IllegalArgumentException, IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(VdxLending.class).withHeader();
        List<VdxLending> data = vdxLendingRepo.getVdxLending(VdxCampus.fromCode(campusCode).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER), startDate, endDate);
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/lending_unfilled.csv", produces = {"text/csv"})
    public void getLendingUnfilledCsv(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxLendingUnfilledDetail.class).withHeader();
        List<SpVdxLendingUnfilledDetail> data = vdxLendingUnfilledRepo
                .getLendingUnfilledDetail(VdxCampus.fromCode(campusCode)
                        .map(VdxCampus::getCode).orElse(""), startDate, endDate)
                .collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/lending_billing.csv", produces = {"text/csv"})
    public void getLendingBillingCsv(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxLendingBilling.class).withHeader();
        List<SpVdxLendingBilling> data = vdxLendingBillingRepo
                .getLendingBilling(VdxCampus.fromCode(campusCode)
                        .map(VdxCampus::getCode).orElse(""), startDate, endDate)
                .collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }

}
