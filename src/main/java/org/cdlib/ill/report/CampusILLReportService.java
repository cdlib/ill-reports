package org.cdlib.ill.report;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.cdlib.ill.model.CampusILLReport;
import org.cdlib.ill.model.InstitutionILLReport;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingSummary;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.cdlib.ill.report.vdx.procedures.SpVdxBorrowingSummaryRepository;
import org.cdlib.ill.report.vdx.procedures.SpVdxLendingSummaryRepository;
import org.springframework.stereotype.Service;

@Transactional(readOnly = true)
@Service
public class CampusILLReportService {

  @Autowired
  SpVdxBorrowingSummaryRepository vdxBorrowingRepo;
  @Autowired
  SpVdxLendingSummaryRepository vdxLendingRepo;

  private void updateBorrowingTotalsByCategory(InstitutionILLReport institution, SpVdxBorrowingSummary summary) {
    switch (summary.getRespCategory()) {
      case ISOPartners:
        institution.setTotalISOBorrowing(summary.getCount());
        break;
      case OCLC:
        institution.setTotalOCLCBorrowing(summary.getCount());
        break;
      case UC:
        institution.setTotalUCBorrowing(summary.getCount());
        break;
    }
  }

  private void updateLendingTotalsByCategory(InstitutionILLReport institution, SpVdxLendingSummary summary) {
    switch (summary.getReqCategory()) {
      case ISOPartners:
        institution.setTotalISOLending(summary.getCount());
        break;
      case OCLC:
        institution.setTotalOCLCLending(summary.getCount());
        break;
      case UC:
        institution.setTotalUCLending(summary.getCount());
        break;
    }
  }

  private void addInstitution(CampusILLReport campus, String campusCode, String institutionName, List<SpVdxBorrowingSummary> borrowingSummaries, List<SpVdxLendingSummary> lendingSummaries) {
    InstitutionILLReport institution = new InstitutionILLReport();
    institution.setCampus(campusCode);
    institution.setName(institutionName);
    borrowingSummaries.forEach((SpVdxBorrowingSummary summary) -> {
      updateBorrowingTotalsByCategory(institution, summary);
    });
    lendingSummaries.forEach((SpVdxLendingSummary summary) -> {
      updateLendingTotalsByCategory(institution, summary);
    });
    campus.getInstitutionReports().add(institution);
  }

  public CampusILLReport getILLCampusReport(String title, String campusCode, String campusQuery, LocalDate from, LocalDate to) {
    CampusILLReport campus = new CampusILLReport(title, campusCode);
    campus.setReportBeginDate(from);
    campus.setReportEndDate(to);

    Map<String, List<SpVdxBorrowingSummary>> borrowingSummaries = vdxBorrowingRepo.getBorrowingSummary(campusQuery, from, to)
        .collect(Collectors.groupingBy(SpVdxBorrowingSummary::getReqName));
    Map<String, List<SpVdxLendingSummary>> lendingSummaries = vdxLendingRepo.getLendingSummary(campusQuery, from, to)
        .collect(Collectors.groupingBy(SpVdxLendingSummary::getRespName));

    Set<String> libraries = Stream.concat(borrowingSummaries.keySet().stream(), lendingSummaries.keySet().stream()).collect(Collectors.toSet());

    libraries.forEach((String name) -> {
      addInstitution(campus, campusCode, name, borrowingSummaries.getOrDefault(name, Collections.emptyList()), lendingSummaries.getOrDefault(name, Collections.emptyList()));
    });

    return campus;
  }

  public CampusILLReport getILLCampusReport(String campusCode, LocalDate from, LocalDate to) {
    return getILLCampusReport(campusCode, campusCode, campusCode, from, to);
  }

}
