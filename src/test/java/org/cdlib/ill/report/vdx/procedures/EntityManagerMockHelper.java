package org.cdlib.ill.report.vdx.procedures;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.mockito.Mockito;

public final class EntityManagerMockHelper {

  public static void stubNativeQueryResultList(EntityManager em, List<Object[]> result) {
    Query query = Mockito.mock(Query.class);
    Mockito.doReturn(query).when(query).setParameter(Mockito.anyInt(), Mockito.any());
    Mockito.doReturn(result).when(query).getResultList();
    Mockito.doReturn(query).when(em).createNativeQuery(Mockito.any());
  }
}
