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
        return em.createNativeQuery("call sp_vdx_lending_data_extract(?1, ?2, ?3)", VdxLending.class)
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
    }
}
