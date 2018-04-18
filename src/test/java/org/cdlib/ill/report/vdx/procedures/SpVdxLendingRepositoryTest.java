package org.cdlib.ill.report.vdx.procedures;

import java.util.Arrays;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.cdlib.ill.report.vdx.VdxShipDeliveryMethod;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;
import static org.cdlib.ill.report.vdx.procedures.EntityManagerMockHelper.stubNativeQueryResultList;

@RunWith(MockitoJUnitRunner.class)
public class SpVdxLendingRepositoryTest {

  @Mock
  private EntityManager em;
  @InjectMocks
  private SpVdxLendingRepository repo;

  @Test
  public void testGetLending() {
    stubNativeQueryResultList(em, Arrays.asList(
        new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
        new Object[]{"UCB", "Library B", "UC Library", "Library C", "Loan", "Courier", "2"}
    ));
    Assert.assertEquals(Sets.newSet(
        new SpVdxLending(VdxCampus.Berkeley, "Library A", "UC Library", "Library B", VdxServiceType.Loan, VdxShipDeliveryMethod.Courier, 1L),
        new SpVdxLending(VdxCampus.Berkeley, "Library B", "UC Library", "Library C", VdxServiceType.Loan, VdxShipDeliveryMethod.Courier, 2L)
    ),
        repo.getLending(null, null, null).collect(Collectors.toSet())
    );
  }

  @Test
  public void testGetLendingWhenCampusIsBlank() {
    stubNativeQueryResultList(em, Arrays.asList(
        new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
        new Object[]{"", "Library B", "UC Library", "Library C", "Loan", "Courier", "2"}
    ));
    Assert.assertEquals(Sets.newSet(
        new SpVdxLending(VdxCampus.Berkeley, "Library A", "UC Library", "Library B", VdxServiceType.Loan, VdxShipDeliveryMethod.Courier, 1L),
        new SpVdxLending(VdxCampus.None, "Library B", "UC Library", "Library C", VdxServiceType.Loan, VdxShipDeliveryMethod.Courier, 2L)
    ),
        repo.getLending(null, null, null).collect(Collectors.toSet())
    );
  }

  @Test
  public void testGetLendingWhenDeliveryMethodIsBlank() {
    stubNativeQueryResultList(em, Arrays.asList(
        new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
        new Object[]{"UCB", "Library B", "UC Library", "Library C", "Loan", "", "2"}
    ));
    Assert.assertEquals(Sets.newSet(
        new SpVdxLending(VdxCampus.Berkeley, "Library A", "UC Library", "Library B", VdxServiceType.Loan, VdxShipDeliveryMethod.Courier, 1L),
        new SpVdxLending(VdxCampus.Berkeley, "Library B", "UC Library", "Library C", VdxServiceType.Loan, VdxShipDeliveryMethod.Other, 2L)
    ),
        repo.getLending(null, null, null).collect(Collectors.toSet())
    );
  }

}
