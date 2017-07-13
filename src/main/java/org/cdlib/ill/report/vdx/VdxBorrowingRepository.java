package org.cdlib.ill.report.vdx;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public class VdxBorrowingRepository {

    @Autowired
    private EntityManager em;

    public List<VdxBorrowing> getVdxBorrowing(String campus, LocalDate beginDate, LocalDate endDate) {
        return em.createNativeQuery("call sp_vdx_borrowing_data_extract(?1, ?2, ?3)", VdxBorrowing.class)
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
    }

}
