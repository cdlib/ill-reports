package org.cdlib.ill.report;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import org.cdlib.ill.report.vdx.VdxBorrowing;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxLending;
import org.cdlib.ill.report.vdx.VdxBorrowingRepository;
import org.cdlib.ill.report.vdx.VdxLendingRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingDetail;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingDetailRepository;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * A web service for ILL data provided by VDX.
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
  @Autowired
  private SpVdxBorrowingDetailRepository vdxBorrowingDetailRepo;


  @RequestMapping(value = "{campusCode}/borrowing_detail_{startDate}_{endDate}.csv", produces = {"text/csv"})
  public void getBorrowingDetailCsv(Writer output,
      @PathVariable("campusCode") String campusCode,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = mapper.schemaFor(SpVdxBorrowingDetail.class).withHeader();
    List<SpVdxBorrowingDetail> data = vdxBorrowingDetailRepo
        .getBorrowingDetail(
            VdxCampus.fromCode(campusCode)
                .map(VdxCampus::getCode)
                .orElse("%"),
            startDate, endDate);
    mapper.writer(schema).writeValue(output, data);
  }

  @RequestMapping(value = "{campusCode}/borrowing_{startDate}_{endDate}.xml", produces = {"application/xml"})
  public HttpEntity<List<VdxBorrowing>> getCampusBorrowingXml(@PathVariable("campusCode") String campusCode,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
    return new ResponseEntity<>(vdxBorrowingRepo.getVdxBorrowing(
        VdxCampus.fromCode(campusCode)
            .map(EnumSet::of)
            .orElse(EnumSet.of(VdxCampus.None, VdxCampus.values())),
        startDate, endDate), HttpStatus.OK);
  }

  @RequestMapping(value = "{campusCode}/borrowing_{startDate}_{endDate}.json", produces = {"application/json"})
  public HttpEntity<List<VdxBorrowing>> getCampusBorrowingJson(@PathVariable("campusCode") String campusCode,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
    return new ResponseEntity<>(vdxBorrowingRepo.getVdxBorrowing(
        VdxCampus.fromCode(campusCode)
            .map(EnumSet::of)
            .orElse(EnumSet.of(VdxCampus.None, VdxCampus.values())),
        startDate, endDate), HttpStatus.OK);
  }

  @RequestMapping(value = "{campusCode}/borrowing_{startDate}_{endDate}.csv", produces = {"text/csv"})
  public void getCampusBorrowingCsv(Writer output,
      @PathVariable("campusCode") String campusCode,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate)
      throws IOException {
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = mapper.schemaFor(VdxBorrowing.class).withHeader();
    List<VdxBorrowing> data = vdxBorrowingRepo.getVdxBorrowing(
        VdxCampus.fromCode(campusCode)
            .map(EnumSet::of)
            .orElse(EnumSet.of(VdxCampus.None, VdxCampus.values())),
        startDate, endDate);
    mapper.writer(schema).writeValue(output, data);
  }

  @RequestMapping(value = "{campusCode}/lending_{startDate}_{endDate}.xml", produces = {"application/xml"})
  public HttpEntity<List<VdxLending>> getCampusLendingXml(@PathVariable("campusCode") String campusCode,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
    return new ResponseEntity<>(vdxLendingRepo.getVdxLending(
        VdxCampus.fromCode(campusCode)
            .map(EnumSet::of)
            .orElse(EnumSet.of(VdxCampus.None, VdxCampus.values())),
        startDate, endDate), HttpStatus.OK);
  }

  @RequestMapping(value = "{campusCode}/lending_{startDate}_{endDate}.json", produces = {"application/json"})
  public HttpEntity<List<VdxLending>> getCampusLendingJson(@PathVariable("campusCode") String campusCode,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
    return new ResponseEntity<>(vdxLendingRepo.getVdxLending(
        VdxCampus.fromCode(campusCode)
            .map(EnumSet::of)
            .orElse(EnumSet.of(VdxCampus.None, VdxCampus.values())),
        startDate, endDate), HttpStatus.OK);
  }

  @RequestMapping(value = "{campusCode}/lending_{startDate}_{endDate}.csv", produces = {"text/csv"})
  public void getCampusLendingCsv(Writer output,
      @PathVariable("campusCode") String campusCode,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate)
      throws IOException {
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = mapper.schemaFor(VdxLending.class).withHeader();
    List<VdxLending> data = vdxLendingRepo.getVdxLending(
        VdxCampus.fromCode(campusCode)
            .map(EnumSet::of)
            .orElse(EnumSet.of(VdxCampus.None, VdxCampus.values())),
        startDate, endDate);
    mapper.writer(schema).writeValue(output, data);
  }

  @RequestMapping(value = "{campusCode}/lending_unfilled_{startDate}_{endDate}.csv", produces = {"text/csv"})
  public void getLendingUnfilledCsv(Writer output,
      @PathVariable("campusCode") String campusCode,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = mapper.schemaFor(SpVdxLendingUnfilledDetail.class).withHeader();
    List<SpVdxLendingUnfilledDetail> data = vdxLendingUnfilledRepo
        .getLendingUnfilledDetail(VdxCampus.fromCode(campusCode)
            .map(VdxCampus::getCode).orElse("%"), startDate, endDate)
        .collect(Collectors.toList());
    mapper.writer(schema).writeValue(output, data);
  }

  @RequestMapping(value = "{campusCode}/lending_billing_{startDate}_{endDate}.csv", produces = {"text/csv"})
  public void getLendingBillingCsv(Writer output,
      @PathVariable("campusCode") String campusCode,
      @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
    CsvMapper mapper = new CsvMapper();
    CsvSchema schema = mapper.schemaFor(SpVdxLendingBilling.class).withHeader();
    List<SpVdxLendingBilling> data = vdxLendingBillingRepo
        .getLendingBilling(
            VdxCampus.fromCode(campusCode)
                .map(VdxCampus::getCode)
                .orElse("%"),
            startDate, endDate);
    mapper.writer(schema).writeValue(output, data);
  }
  


}
