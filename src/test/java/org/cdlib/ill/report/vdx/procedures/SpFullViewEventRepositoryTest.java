package org.cdlib.ill.report.vdx.procedures;

import static org.cdlib.ill.report.vdx.procedures.EntityManagerMockHelper.stubNativeQueryResultList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxILLCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpFullViewEventRepositoryTest {

  @Mock
  private EntityManager em;
  @InjectMocks
  private SpFullViewEventRepository repo;
  
  @Test
  public void testGetFullViewEvents() {
    stubNativeQueryResultList(em, Arrays.asList(
        new Object[]{"UCB", "1", "2", "3", "4", "5", "6"},
        new Object[]{"UCLA", "1", "2", "3", "4", "5", "6"},
        new Object[]{"UCB", "4", "8", "9", "7", "2", "4"}
    ));
    Assert.assertEquals(Sets.newSet(new SpFullViewEvents("UCB", Long.valueOf(1), Long.valueOf(2), Long.valueOf(3), Long.valueOf(4), Long.valueOf(5), Long.valueOf(6)),
        new SpFullViewEvents("UCLA", Long.valueOf(1), Long.valueOf(2), Long.valueOf(3), Long.valueOf(4), Long.valueOf(5), Long.valueOf(6)),
        new SpFullViewEvents("UCB", Long.valueOf(4), Long.valueOf(8), Long.valueOf(9), Long.valueOf(7), Long.valueOf(2), Long.valueOf(4))),
        repo.getFullViewEvents(null, null).collect(Collectors.toSet())
    );
  }
}
