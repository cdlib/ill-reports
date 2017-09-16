package org.cdlib.ill.report.api;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUC;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingOCLCRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUCRepository;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingOCLC;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingPatron;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingPatronRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingSummary;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingSummaryRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingTat;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingTatRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUnfilledSummary;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingUnfilledSummaryRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxCopyright;
import org.cdlib.ill.report.vdx.procedures.SpVdxCopyrightRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxJournalBorrowing;
import org.cdlib.ill.report.vdx.procedures.SpVdxJournalBorrowingRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLending;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingPatron;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingPatronRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingSummary;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingSummaryRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingTat;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingTatRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingUnfilledSummary;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingUnfilledSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * A web service for precomputed ILL statistics.
 *
 * TODO: Throw an exception => 400 when the campus is not an enumerated value &
 * unit test.
 *
 * TODO: Refactor redundant CSV marshalling.
 *
 * @author mmorrisp
 */
@RestController
@RequestMapping("/ill/stats/by-campus/")
public class CampusILLStatisticsRestController {

    @Autowired
    SpVdxBorrowingSummaryRepository spVdxBorrowingSummaryRepo;
    @Autowired
    SpVdxLendingSummaryRepository spVdxLendingSummaryRepo;
    @Autowired
    private SpVdxBorrowingUCRepository spVdxBorrowingUCRepo;
    @Autowired
    private SpVdxBorrowingOCLCRepository spVdxBorrowingOCLCRepo;
    @Autowired
    private SpVdxLendingRepository spVdxLendingRepo;
    @Autowired
    private SpVdxCopyrightRepository spVdxCopyrightRepo;
    @Autowired
    private SpVdxJournalBorrowingRepository spVdxJournalBorrowingRepo;
    @Autowired
    private SpVdxBorrowingPatronRepository spVdxBorrowingPatronRepo;
    @Autowired
    private SpVdxLendingPatronRepository spVdxLendingPatronRepo;
    @Autowired
    private SpVdxBorrowingUnfilledSummaryRepository spVdxBorrowingUnfilledSummaryRepo;
    @Autowired
    private SpVdxLendingUnfilledSummaryRepository spVdxLendingUnfilledSummaryRepo;
    @Autowired
    private SpVdxBorrowingTatRepository spVdxBorrowingTatRepo;
    @Autowired
    private SpVdxLendingTatRepository spVdxLendingTatRepo;

    @RequestMapping(value = "{campusCode}/borrowing_summary.csv", produces = {"text/csv"})
    public void getVdxBorrowingSummary(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxBorrowingSummary.class).withHeader();
        List<SpVdxBorrowingSummary> data = spVdxBorrowingSummaryRepo.getBorrowingSummary(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }
    
    @RequestMapping(value = "{campusCode}/lending_summary.csv", produces = {"text/csv"})
    public void getVdxLendingSummary(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxLendingSummary.class).withHeader();
        List<SpVdxLendingSummary> data = spVdxLendingSummaryRepo.getLendingSummary(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }
    
    @RequestMapping(value = "{campusCode}/borrowing_uc.csv", produces = {"text/csv"})
    public void getVdxBorrowingUC(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxBorrowingUC.class).withHeader();
        List<SpVdxBorrowingUC> data = spVdxBorrowingUCRepo.getBorrowingUC(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/borrowing_oclc.csv", produces = {"text/csv"})
    public void getVdxBorrowingOCLC(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxBorrowingOCLC.class).withHeader();
        List<SpVdxBorrowingOCLC> data = spVdxBorrowingOCLCRepo.getBorrowingOCLC(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/lending.csv", produces = {"text/csv"})
    public void getVdxLending(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxLending.class).withHeader();
        List<SpVdxLending> data = spVdxLendingRepo.getLending(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/copyright.csv", produces = {"text/csv"})
    public void getVdxCopyright(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxCopyright.class).withHeader();
        List<SpVdxCopyright> data = spVdxCopyrightRepo.getCopyright(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/journal_borrowing.csv", produces = {"text/csv"})
    public void getVdxJournalBorrowing(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxJournalBorrowing.class).withHeader();
        List<SpVdxJournalBorrowing> data = spVdxJournalBorrowingRepo.getJournalBorrowing(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/borrowing_patron.csv", produces = {"text/csv"})
    public void getVdxBorrowingPatron(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxBorrowingPatron.class).withHeader();
        List<SpVdxBorrowingPatron> data = spVdxBorrowingPatronRepo.getBorrowingPatron(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/lending_patron.csv", produces = {"text/csv"})
    public void getVdxLendingPatron(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxLendingPatron.class).withHeader();
        List<SpVdxLendingPatron> data = spVdxLendingPatronRepo.getLendingPatron(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/borrowing_unfilled.csv", produces = {"text/csv"})
    public void getVdxBorrowingUnfilled(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxBorrowingUnfilledSummary.class).withHeader();
        List<SpVdxBorrowingUnfilledSummary> data = spVdxBorrowingUnfilledSummaryRepo.getBorrowingUnfilledSummary(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }
    
    @RequestMapping(value = "{campusCode}/lending_unfilled.csv", produces = {"text/csv"})
    public void getVdxLendingUnfilled(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxLendingUnfilledSummary.class).withHeader();
        List<SpVdxLendingUnfilledSummary> data = spVdxLendingUnfilledSummaryRepo.getLendingUnfilledSummary(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/borrowing_tat.csv", produces = {"text/csv"})
    public void getVdxBorrowingTat(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxBorrowingTat.class).withHeader();
        List<SpVdxBorrowingTat> data = spVdxBorrowingTatRepo.getBorrowingTat(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }

    @RequestMapping(value = "{campusCode}/lending_tat.csv", produces = {"text/csv"})
    public void getVdxLendingTat(Writer output,
            @PathVariable("campusCode") String campusCode,
            @RequestParam(required = false, name = "startDate", defaultValue = "1900-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2100-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(SpVdxLendingTat.class).withHeader();
        List<SpVdxLendingTat> data = spVdxLendingTatRepo.getLendingTat(VdxCampus.fromCode(campusCode).map(VdxCampus::getCode).orElse("%"), startDate, endDate).collect(Collectors.toList());
        mapper.writer(schema).writeValue(output, data);
    }
    
}
