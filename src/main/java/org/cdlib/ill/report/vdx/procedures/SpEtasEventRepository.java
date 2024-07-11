package org.cdlib.ill.report.vdx.procedures;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jakarta.persistence.EntityManager;
import org.cdlib.ill.report.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(readOnly = true)
@Repository
public class SpEtasEventRepository {

  @Autowired
  private EntityManager em;
  
  public Stream<SpEtasEvents> getEtasEvents(LocalDate beginDate, LocalDate endDate) {
    List<Object[]> results = em.createNativeQuery("call sp_etas_events(?1, ?2)")
        .setParameter(1, beginDate)
        .setParameter(2, endDate)
        .getResultList();
    return results.stream().map((Object[] values) -> {
      Assert.isTrue(values.length == 9, Constants.BAD_PROCEDURE_MSG);
      Assert.noNullElements(values, Constants.NULL_DATA_MSG);
      return new SpEtasEvents(
          String.valueOf(values[0]),
          Long.valueOf(String.valueOf(values[1])),
          Long.valueOf(String.valueOf(values[2])),
          Long.valueOf(String.valueOf(values[3])),
          Long.valueOf(String.valueOf(values[4])),
          Long.valueOf(String.valueOf(values[5])),
          Long.valueOf(String.valueOf(values[6])),
          Long.valueOf(String.valueOf(values[7])),
          Long.valueOf(String.valueOf(values[8]))
      );
    });
  }
}
