package org.cdlib.ill.report.vdx;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public class VdxLendingRepository {

    @Autowired
    private EntityManager em;

    public List<VdxLending> getVdxLending(VdxCampus campus, LocalDate beginDate, LocalDate endDate) {
        return em.createQuery("select l from VdxLending l where l.lender.campus = :campus and l.entryDate >= :beginDate and l.entryDate < :endDate", VdxLending.class)
                .setParameter("campus", campus)
                .setParameter("beginDate", beginDate.atStartOfDay())
                .setParameter("endDate", endDate.atStartOfDay())
                .getResultList();
    }
}
