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
public class VdxLendingRepository {

  @Autowired
  private EntityManager em;

  public List<VdxLending> getVdxLending(Set<VdxCampus> campusFilter, LocalDate beginDate, LocalDate endDate) {
    return em.createQuery("select l from VdxLending l where l.lender.campus in :campusFilter and l.entryDate >= :beginDate and l.entryDate < :endDate", VdxLending.class)
        .setParameter("campusFilter", campusFilter)
        .setParameter("beginDate", beginDate.atStartOfDay())
        .setParameter("endDate", endDate.atStartOfDay())
        .getResultList();
  }
  
}
