package org.cdlib.ill.report.vdx.schedule_c;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.Constants;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.cdlib.ill.report.vdx.VdxShipDeliveryMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Native queries necessary to produce a Schedule C report.
 */
@Transactional(readOnly = true)
@Repository
public class ScheduleCRepository {

  @Autowired
  private EntityManager em;

  public List<UCBorrowingSubtotal> getBorrowing(String campus, LocalDate beginDate, LocalDate endDate) {
    List<Object[]> results = em.createNativeQuery("select req_campus, req_name, resp_campus, resp_name, \n"
        + "        servicetp, ship_delivery_method, count(*) from vdx_borrowing\n"
        + "        where \n"
        + "        req_campus like ?\n"
        + "        and rec_date >= ?\n"
        + "        and rec_date < ?\n"
        + "        and resp_category in ('I','U')\n"
        + "        group by req_campus, req_name, resp_campus, resp_name, \n"
        + "servicetp, ship_delivery_method;")
        .setParameter(1, campus)
        .setParameter(2, beginDate)
        .setParameter(3, endDate)
        .getResultList();
    return results.stream().map((Object[] values) -> {
      Assert.noNullElements(values, Constants.NULL_DATA_MSG);
      return new UCBorrowingSubtotal(
          VdxCampus.fromCode(String.valueOf(values[0])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
          String.valueOf(values[1]),
          VdxCampus.fromCode(String.valueOf(values[2])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
          String.valueOf(values[3]),
          VdxServiceType.fromCode(String.valueOf(values[4])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
          VdxShipDeliveryMethod.fromCode(String.valueOf(values[5])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
          Long.valueOf(String.valueOf(values[6])));
    }).collect(Collectors.toList());
  }

  public List<LendingSubtotal> getLending(String campus, LocalDate beginDate, LocalDate endDate) {
    List<Object[]> results = em.createNativeQuery("select resp_campus, resp_name, req_loctype, req_campus, req_name, \n"
        + "        servicetp, ship_delivery_method, count(*) from vdx_lending \n"
        + "        where \n"
        + "        resp_campus like ?\n"
        + "        and date_shipped >= ?\n"
        + "        and date_shipped < ?\n"
        + "        group by resp_campus, resp_name, req_loctype, req_campus, req_name, \n"
        + "servicetp, ship_delivery_method;")
        .setParameter(1, campus)
        .setParameter(2, beginDate)
        .setParameter(3, endDate)
        .getResultList();
    return results.stream().map((Object[] values) -> {
      Assert.noNullElements(values, Constants.NULL_DATA_MSG);
      return new LendingSubtotal(
          VdxCampus.fromCode(String.valueOf(values[0])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
          String.valueOf(values[1]),
          String.valueOf(values[2]),
          VdxCampus.fromCode(String.valueOf(values[3])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
          String.valueOf(values[4]),
          VdxServiceType.fromCode(String.valueOf(values[5])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
          VdxShipDeliveryMethod.fromCode(String.valueOf(values[6])).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER),
          Long.valueOf(String.valueOf(values[7])));
    }).collect(Collectors.toList());
  }
}
