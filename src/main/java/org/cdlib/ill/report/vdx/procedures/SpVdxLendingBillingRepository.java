package org.cdlib.ill.report.vdx.procedures;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.EntityManager;
import org.cdlib.ill.report.Constants;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(readOnly = true)
@Repository
public class SpVdxLendingBillingRepository {

  private static final AttributeConverter<LocalDate, Date> LOCAL_DATE_CONVERTER = new Jsr310JpaConverters.LocalDateConverter();

  @Autowired
  private EntityManager em;

  /**
   * Note that {@code dateShipped} is a nullable field in the database, but the
   * procedure should never return a null value.
   *
   * @param campusCode
   * @param beginDate
   * @param endDate
   * @return
   */
  public List<SpVdxLendingBilling> getLendingBilling(String campusCode, LocalDate beginDate, LocalDate endDate) {
    List<Object[]> results = em.createNativeQuery("call sp_vdx_lending_billing(?1, ?2, ?3)", SpVdxLendingBilling.RESULT_SET_MAPPING)
        .setParameter(1, campusCode)
        .setParameter(2, beginDate)
        .setParameter(3, endDate)
        .getResultList();
    return results.stream().map((Object[] values) -> {
      Assert.isTrue(values.length == 26, Constants.BAD_PROCEDURE_MSG);
      Assert.notNull(new Object[]{
        values[0],
        values[2],
        values[6],
        values[8],
        values[9],
        values[10],
        values[11],
        values[12],
        values[13],
        values[14],
        values[15],
        values[16],
        values[17],
        values[18],
        values[19],
        values[20],
        values[21],
        values[22],
        values[23]
      }, Constants.NULL_DATA_MSG);
      return new SpVdxLendingBilling(
          Long.valueOf(String.valueOf(values[0])),
          String.valueOf(values[2]),
          checkNullDecimal(String.valueOf(values[3])),
          checkNullDecimal(String.valueOf(values[4])),
          checkNullDecimal(String.valueOf(values[5])),
          LOCAL_DATE_CONVERTER.convertToEntityAttribute((Date) values[6]),
          checkNullLong(String.valueOf(values[7])),
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
          checkNullDecimal(String.valueOf(values[24])));
    }).collect(Collectors.toList());
  }

  private BigDecimal checkNullDecimal(String decimalString) {
    return "null".equals(decimalString) ? null : new BigDecimal(decimalString);
  }

  private Long checkNullLong(String longString) {
    return "null".equals(longString) ? null : Long.parseLong(longString);
  }
}
