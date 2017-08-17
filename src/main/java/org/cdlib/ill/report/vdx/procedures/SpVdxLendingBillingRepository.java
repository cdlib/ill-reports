package org.cdlib.ill.report.vdx.procedures;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public class SpVdxLendingBillingRepository {

    @Autowired
    private EntityManager em;

    public List<SpVdxLendingBilling> getLendingBilling(String campusCode, LocalDate beginDate, LocalDate endDate) {
        return em.createNativeQuery("call sp_vdx_lending_billing(?1, ?2, ?3)", SpVdxLendingBilling.RESULT_SET_MAPPING)
                .setParameter(1, campusCode)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
    }
}
