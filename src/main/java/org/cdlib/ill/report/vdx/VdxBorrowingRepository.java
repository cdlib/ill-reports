package org.cdlib.ill.report.vdx;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public class VdxBorrowingRepository {

  @Autowired
  private EntityManager em;

  public List<VdxBorrowing> getVdxBorrowing(Set<VdxCampus> campusFilter, LocalDate beginDate, LocalDate endDate) {
    return em.createQuery("select b from VdxBorrowing b where b.borrower.campus in :campusFilter and b.entryDate >= :beginDate and b.entryDate < :endDate", VdxBorrowing.class)
        .setParameter("campusFilter", campusFilter)
        .setParameter("beginDate", beginDate.atStartOfDay())
        .setParameter("endDate", endDate.atStartOfDay())
        .getResultList();
  }

}
