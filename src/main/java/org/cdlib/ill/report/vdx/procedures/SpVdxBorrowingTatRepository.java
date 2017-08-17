package org.cdlib.ill.report.vdx.procedures;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.Constants;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.cdlib.ill.report.vdx.VdxShipDeliveryMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(readOnly = true)
@Repository
public class SpVdxBorrowingTatRepository {

    @Autowired
    private EntityManager em;

    public Stream<SpVdxBorrowingTat> getBorrowingTat(String campus, LocalDate beginDate, LocalDate endDate) {
        List<Object[]> results = em.createNativeQuery("call sp_vdx_borrowing_tat(?1, ?2, ?3)")
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
        return results.stream().map((Object[] values) -> {
            Assert.isTrue(values.length == 6, Constants.BAD_PROCEDURE_MSG);
            Assert.noNullElements(values, Constants.NULL_DATA_MSG);
            return new SpVdxBorrowingTat(
                    VdxCampus.fromCode(String.valueOf(values[0])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
                    String.valueOf(values[1]),
                    Long.valueOf(String.valueOf(values[2])),
                    VdxServiceType.fromCode(String.valueOf(values[3])).orElseThrow(() -> {
                        return Constants.BAD_DATA_EX;
                    }),
                    VdxShipDeliveryMethod.fromCode(String.valueOf(values[4])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
                    Long.valueOf(String.valueOf(values[5])));
        });
    }

}
