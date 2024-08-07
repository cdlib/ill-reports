package org.cdlib.ill.report.vdx.procedures;

import static org.cdlib.ill.report.vdx.procedures.EntityManagerMockHelper.stubNativeQueryResultList;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;
import jakarta.persistence.EntityManager;

@RunWith(MockitoJUnitRunner.class)
public class SpEtasEventRepositoryTest {

  @Mock
  private EntityManager em;
  @InjectMocks
  private SpEtasEventRepository repo;
  
  @Test
  public void testGetEtasEvents() {
    stubNativeQueryResultList(em, Arrays.asList(
        new Object[]{"UCB", "1", "2", "3", "4", "5", "6", "7", "8"},
        new Object[]{"UCLA", "1", "2", "3", "4", "5", "6", "7", "8"},
        new Object[]{"UCB", "4", "8", "9", "7", "2", "4", "9", "0"}
    ));
    Assert.assertEquals(Sets.newSet(new SpEtasEvents("UCB", Long.valueOf(1), Long.valueOf(2), Long.valueOf(3), Long.valueOf(4), Long.valueOf(5), Long.valueOf(6), Long.valueOf(7), Long.valueOf(8)),
        new SpEtasEvents("UCLA", Long.valueOf(1), Long.valueOf(2), Long.valueOf(3), Long.valueOf(4), Long.valueOf(5), Long.valueOf(6), Long.valueOf(7), Long.valueOf(8)),
        new SpEtasEvents("UCB", Long.valueOf(4), Long.valueOf(8), Long.valueOf(9), Long.valueOf(7), Long.valueOf(2), Long.valueOf(4), Long.valueOf(9), Long.valueOf(0))),
        repo.getEtasEvents(null, null).collect(Collectors.toSet())
    );
  }
}
