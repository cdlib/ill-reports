package org.cdlib.ill.report.vdx.procedures;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.Constants;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(readOnly = true)
@Repository
public class SpVdxLendingBillingRepository {

    @Autowired
    private EntityManager em;

    public Stream<SpVdxLendingBilling> getLendingBilling(String campus, LocalDate beginDate, LocalDate endDate) {
        List<Object[]> results = em.createNativeQuery("call sp_vdx_lending_billing(?1, ?2, ?3)")
                .setParameter(1, campus)
                .setParameter(2, beginDate)
                .setParameter(3, endDate)
                .getResultList();
        return results.stream().map((Object[] values) -> {
            Assert.isTrue(values.length == 26, Constants.BAD_PROCEDURE_MSG);
            Assert.noNullElements(values, Constants.NULL_DATA_MSG);
            return new SpVdxLendingBilling(
                    Long.valueOf(String.valueOf(values[0])),
                    String.valueOf(values[2]),
                    new BigDecimal(String.valueOf(values[3])),
                    new BigDecimal(String.valueOf(values[4])),
                    new BigDecimal(String.valueOf(values[5])),
                    LocalDate.parse(String.valueOf(values[6])),
                    Long.valueOf(String.valueOf(values[7])),
                    String.valueOf(values[8]),
                    String.valueOf(values[9]),
                    String.valueOf(values[10]),
                    String.valueOf(values[11]),
                    String.valueOf(values[12]),
                    String.valueOf(values[13]),
                    String.valueOf(values[14]),
                    String.valueOf(values[15]),
                    String.valueOf(values[16]),
                    String.valueOf(values[17]),
                    String.valueOf(values[18]),
                    String.valueOf(values[19]),
                    VdxServiceType.fromCode(String.valueOf(values[20])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
                    String.valueOf(values[21]),
                    String.valueOf(values[22]),
                    String.valueOf(values[23]),
                    new BigDecimal(String.valueOf(values[24])));
        });
    }
}
