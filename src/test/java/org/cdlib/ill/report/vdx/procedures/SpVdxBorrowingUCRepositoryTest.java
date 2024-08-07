package org.cdlib.ill.report.vdx.procedures;

import java.util.Arrays;
import java.util.stream.Collectors;
import jakarta.persistence.EntityManager;
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
public class SpVdxBorrowingUCRepositoryTest {

  @Mock
  private EntityManager em;
  @InjectMocks
  private SpVdxBorrowingUCRepository repo;

  @Test
  public void testGetBorrowingUC() {
    stubNativeQueryResultList(em, Arrays.asList(
        new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "", "1"},
        new Object[]{"UCI", "Library B", "Library C", "Copy non returnable", "Courier", "2"},
        new Object[]{"UCI", "Library C", "Library D", "Copy non returnable", "Email", "3"},
        new Object[]{"UCI", "Library D", "Library E", "Loan", "FTP", "4"},
        new Object[]{"UCI", "Library E", "Library A", "Loan", "Postal", "5"}
    ));
    Assert.assertEquals(Sets.newSet(new SpVdxBorrowingUC(VdxCampus.Irvine, "Library A", "Library B", VdxServiceType.CopyNonReturnable, VdxShipDeliveryMethod.Other, 1L),
        new SpVdxBorrowingUC(VdxCampus.Irvine, "Library B", "Library C", VdxServiceType.CopyNonReturnable, VdxShipDeliveryMethod.Courier, 2L),
        new SpVdxBorrowingUC(VdxCampus.Irvine, "Library C", "Library D", VdxServiceType.CopyNonReturnable, VdxShipDeliveryMethod.Email, 3L),
        new SpVdxBorrowingUC(VdxCampus.Irvine, "Library D", "Library E", VdxServiceType.Loan, VdxShipDeliveryMethod.FTP, 4L),
        new SpVdxBorrowingUC(VdxCampus.Irvine, "Library E", "Library A", VdxServiceType.Loan, VdxShipDeliveryMethod.Postal, 5L)
    ),
        repo.getBorrowingUC(null, null, null).collect(Collectors.toSet())
    );
  }

  @Test
  public void testGetBorrowingUCWhenCampusIsBlank() {
    stubNativeQueryResultList(em, Arrays.asList(
        new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "", "1"},
        new Object[]{"", "Library B", "Library A", "Copy non returnable", "", "2"}
    ));
    Assert.assertEquals(Sets.newSet(new SpVdxBorrowingUC(VdxCampus.Irvine, "Library A", "Library B", VdxServiceType.CopyNonReturnable, VdxShipDeliveryMethod.Other, 1L),
        new SpVdxBorrowingUC(VdxCampus.None, "Library B", "Library A", VdxServiceType.CopyNonReturnable, VdxShipDeliveryMethod.Other, 2L)
    ),
        repo.getBorrowingUC(null, null, null).collect(Collectors.toSet())
    );
  }

  @Test
  public void testGetBorrowingUCWhenDeliveryMethodIsBlank() {
    stubNativeQueryResultList(em, Arrays.asList(
        new Object[]{"UCI", "Library A", "Library B", "Loan", "", "1"},
        new Object[]{"UCI", "Library B", "Library A", "Loan", "", "2"}
    ));
    Assert.assertEquals(Sets.newSet(new SpVdxBorrowingUC(VdxCampus.Irvine, "Library A", "Library B", VdxServiceType.Loan, VdxShipDeliveryMethod.Other, 1L),
        new SpVdxBorrowingUC(VdxCampus.Irvine, "Library B", "Library A", VdxServiceType.Loan, VdxShipDeliveryMethod.Other, 2L)
    ),
        repo.getBorrowingUC(null, null, null).collect(Collectors.toSet())
    );
  }

}
