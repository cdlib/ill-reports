package org.cdlib.ill.report.vdx;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(readOnly = true)
@Repository
public class VdxBorrowingUCRepository extends VdxBaseRepository {

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
    public Stream<VdxBorrowingByCategory> getBorrowingUC(String campus, LocalDate beginDate, LocalDate endDate) {
        List<Object[]> results = em.createNativeQuery("call sp_vdx_borrowing_uc(?1, ?2, ?3)").setParameter(1, campus).setParameter(2, beginDate).setParameter(3, endDate).getResultList();
        return results.stream().map((Object[] values) -> {
            Assert.isTrue(values.length == 6, BAD_PROCEDURE_MSG);
            Assert.noNullElements(values, NULL_DATA_MSG);
            return new VdxBorrowingByCategory(VdxCampus.fromCode(String.valueOf(values[0])).orElseThrow(() -> {
                return BAD_DATA_EX;
            }), String.valueOf(values[1]), String.valueOf(values[2]), VdxServiceType.fromCode(String.valueOf(values[3])).orElseThrow(() -> {
                return BAD_DATA_EX;
            }), VdxShipDeliveryMethod.fromCode(String.valueOf(values[4])).orElseThrow(() -> {
                return BAD_DATA_EX;
            }), Long.valueOf(String.valueOf(values[5])));
        });
    }

}