package org.cdlib.ill.report.vdx.procedures;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.Constants;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(readOnly = true)
@Repository
public class SpVdxBorrowingOCLCRepository {

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
    public Stream<SpVdxBorrowingOCLC> getBorrowingOCLC(String campus, LocalDate beginDate, LocalDate endDate) {
        List<Object[]> results = em.createNativeQuery("call sp_vdx_borrowing_oclc(?1, ?2, ?3)")
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
        return results.stream().map((Object[] values) -> {
            Assert.isTrue(values.length == 5, Constants.BAD_PROCEDURE_MSG);
            Assert.noNullElements(values, Constants.NULL_DATA_MSG);
            return new SpVdxBorrowingOCLC(
                    VdxCampus.fromCode(String.valueOf(values[0])).orElseThrow(() -> {
                        return Constants.BAD_DATA_EX;
                    }),
                    String.valueOf(values[1]),
                    String.valueOf(values[2]),
                    VdxServiceType.fromCode(String.valueOf(values[3])).orElseThrow(() -> {
                        return Constants.BAD_DATA_EX;
                    }),
                    Long.valueOf(String.valueOf(values[4])));
        });
    }
}
