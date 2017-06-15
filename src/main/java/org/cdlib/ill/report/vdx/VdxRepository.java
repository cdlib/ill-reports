package org.cdlib.ill.report.vdx;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author mmorrisp
 */
@Transactional(readOnly = true)
@Repository
public class VdxRepository {

    @Autowired
    private EntityManager em;

    public Stream<VdxBorrowingSummary> getBorrowingSummary(String campus, LocalDate beginDate, LocalDate endDate) {
        List<Object[]> results = em.createNativeQuery("call sp_vdx_borrowing_summary(?1, ?2, ?3)")
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
        return results.stream().map((Object[] values) -> {
            Assert.noNullElements(values, "Unexpected null database value.");
            return new VdxBorrowingSummary(
                    VdxCampus.fromCode(String.valueOf(values[0])).orElseThrow(() -> {
                        return new RuntimeException();
                    }),
                    String.valueOf(values[1]),
                    VdxCategory.fromCode(String.valueOf(values[2])).get(),
                    Long.valueOf(String.valueOf(values[3])));
        });
    }

    public Stream<VdxLendingSummary> getLendingSummary(String campus, LocalDate beginDate, LocalDate endDate) {
        List<Object[]> results = em.createNativeQuery("call sp_vdx_lending_summary(?1, ?2, ?3)")
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
        return results.stream().map((Object[] values) -> {
            return new VdxLendingSummary(
                    VdxCampus.fromCode(String.valueOf(values[0])).get(),
                    String.valueOf(values[1]),
                    VdxCategory.fromCode(String.valueOf(values[2])).get(),
                    Long.valueOf(String.valueOf(values[3])));
        });
    }

    public List<VdxBorrowing> getVdxBorrowing(String campus, LocalDate beginDate, LocalDate endDate) {
        return em.createNativeQuery("call sp_vdx_borrowing_data_extract(?1, ?2, ?3)", VdxBorrowing.class)
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
    }

    public List<VdxLending> getVdxLending(String campus, LocalDate beginDate, LocalDate endDate) {
        return em.createNativeQuery("call sp_vdx_lending_data_extract(?1, ?2, ?3)", VdxLending.class)
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
    }
    
}
