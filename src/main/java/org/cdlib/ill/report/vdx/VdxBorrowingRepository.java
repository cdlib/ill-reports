package org.cdlib.ill.report.vdx;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mmorrisp
 */
@Transactional
@Repository
public class VdxBorrowingRepository {

    @Autowired
    private EntityManager em;

    public Stream<VdxBorrowingSummary> getBorrowingSummary(String campus, LocalDate beginDate, LocalDate endDate) {
        List<Object[]> results = em.createNativeQuery("call sp_vdx_borrowing_summary(?1, ?2, ?3)")
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
        return results.stream().map((Object[] values) -> {
            return new VdxBorrowingSummary(
                    VdxCampus.fromCode(String.valueOf(values[0])).get(),
                    String.valueOf(values[1]),
                    VdxCategory.fromCode(String.valueOf(values[2])).get(),
                    Long.valueOf(String.valueOf(values[3])));
        });
    }

}
