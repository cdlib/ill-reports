package org.cdlib.ill.report;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.cdlib.ill.model.CampusReport;
import org.cdlib.ill.model.InstitutionReport;
import org.cdlib.ill.report.vdx.VdxBorrowingSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.cdlib.ill.report.vdx.VdxBorrowingRepository;

/**
 * Data warehouse for UC library ILL data, provided solely by VDX.
 *
 * @author mmorrisp
 */
@Transactional(readOnly = true)
@Repository
public class DataWarehouseRepository {

    @Autowired
    VdxBorrowingRepository vdxRepo;

    private void updateTotalsByCategory(InstitutionReport institution, VdxBorrowingSummary summary) {
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

    private void addInstitution(CampusReport campus, String campusCode, String institutionName, List<VdxBorrowingSummary> summaries) {
        InstitutionReport institution = new InstitutionReport();
        institution.setCampus(campusCode);
        institution.setName(institutionName);
        summaries.forEach((VdxBorrowingSummary summary) -> {
            updateTotalsByCategory(institution, summary);
        });
        campus.getInstitutionReports().add(institution);
    }

    public CampusReport getCampusBorrowingSummary(String code, LocalDate from, LocalDate to) {
        CampusReport campus = new CampusReport(code);
        campus.setReportBeginDate(from);
        campus.setReportEndDate(to);
        
        Map<String, List<VdxBorrowingSummary>> institutions = vdxRepo.getBorrowingSummary(code, from, to)
                .collect(Collectors.groupingBy(VdxBorrowingSummary::getReqName));
        institutions.forEach((String name, List<VdxBorrowingSummary> summaries) -> {
            addInstitution(campus, code, name, summaries);
        });
        
        return campus;
    }

}
