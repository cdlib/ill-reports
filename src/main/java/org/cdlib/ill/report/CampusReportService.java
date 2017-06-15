package org.cdlib.ill.report;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.cdlib.ill.model.CampusReport;
import org.cdlib.ill.model.InstitutionReport;
import org.cdlib.ill.report.vdx.VdxBorrowingSummary;
import org.cdlib.ill.report.vdx.VdxLendingSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.cdlib.ill.report.vdx.VdxBorrowingRepository;
import org.cdlib.ill.report.vdx.VdxLendingRepository;
import org.springframework.stereotype.Service;

/**
 * Data warehouse for UC library ILL data, provided solely by VDX.
 *
 * TODO: Refactor verbose & redundant code.
 *
 * @author mmorrisp
 */
@Transactional(readOnly = true)
@Service
public class CampusReportService {

    @Autowired
    VdxBorrowingRepository vdxBorrowingRepo;
    @Autowired
    VdxLendingRepository vdxLendingRepo;

    private void updateBorrowingTotalsByCategory(InstitutionReport institution, VdxBorrowingSummary summary) {
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

    private void updateLendingTotalsByCategory(InstitutionReport institution, VdxLendingSummary summary) {
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

    private void addInstitution(CampusReport campus, String campusCode, String institutionName, List<VdxBorrowingSummary> borrowingSummaries, List<VdxLendingSummary> lendingSummaries) {
        InstitutionReport institution = new InstitutionReport();
        institution.setCampus(campusCode);
        institution.setName(institutionName);
        borrowingSummaries.forEach((VdxBorrowingSummary summary) -> {
            updateBorrowingTotalsByCategory(institution, summary);
        });
        lendingSummaries.forEach((VdxLendingSummary summary) -> {
            updateLendingTotalsByCategory(institution, summary);
        });
        campus.getInstitutionReports().add(institution);
    }

    public CampusReport getCampusReport(String campusCode, LocalDate from, LocalDate to) {
        CampusReport campus = new CampusReport(campusCode);
        campus.setReportBeginDate(from);
        campus.setReportEndDate(to);

        Map<String, List<VdxBorrowingSummary>> borrowingSummaries = vdxBorrowingRepo.getBorrowingSummary(campusCode, from, to)
                .collect(Collectors.groupingBy(VdxBorrowingSummary::getReqName));
        Map<String, List<VdxLendingSummary>> lendingSummaries = vdxLendingRepo.getLendingSummary(campusCode, from, to)
                .collect(Collectors.groupingBy(VdxLendingSummary::getRespName));

        Set<String> libraries = Stream.concat(borrowingSummaries.keySet().stream(), lendingSummaries.keySet().stream()).collect(Collectors.toSet());

        libraries.forEach((String name) -> {
            addInstitution(campus, campusCode, name, borrowingSummaries.getOrDefault(name, Collections.emptyList()), lendingSummaries.getOrDefault(name, Collections.emptyList()));
        });

        return campus;
    }
    
}
