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

    private static final RuntimeException BAD_DATA = new IllegalArgumentException("Unexpected data.");

    @Autowired
    private EntityManager em;

    /**
     *
     * @param campus
     * @param beginDate
     * @param endDate
     * @return
     * @throws IllegalArgumentException When the database contains null values
     * or unexpected campuses or categories. The DDL should forbid null values.
     */
    public Stream<VdxBorrowingSummary> getBorrowingSummary(String campus, LocalDate beginDate, LocalDate endDate) throws IllegalArgumentException {
        List<Object[]> results = em.createNativeQuery("call sp_vdx_borrowing_summary(?1, ?2, ?3)")
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
        return results.stream().map((Object[] values) -> {
            Assert.noNullElements(values, "Unexpected null database value.");
            return new VdxBorrowingSummary(
                    VdxCampus.fromCode(String.valueOf(values[0])).orElseThrow(() -> {
                        return BAD_DATA;
                    }),
                    String.valueOf(values[1]),
                    VdxCategory.fromCode(String.valueOf(values[2])).orElseThrow(() -> {
                        return BAD_DATA;
                    }),
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
