package org.cdlib.ill.report;

import java.time.LocalDate;
import org.cdlib.ill.model.Campus;
import org.cdlib.ill.model.Institution;
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

    public Campus getCampusBorrowingSummary(String code, LocalDate from, LocalDate to) {
        Campus campus = new Campus(code);
        vdxRepo.getBorrowingSummary(code, from, to).forEach((VdxBorrowingSummary summary) -> {
            Institution institution = new Institution();
            institution.setCampus(code);
            institution.setName(summary.getReqName());
            campus.getInstitutions().add(institution);
        });
        return campus;
    }

}
