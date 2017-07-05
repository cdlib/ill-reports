package org.cdlib.ill.report.vdx;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VdxBorrowingUCRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private VdxBorrowingUCRepository repo;

    private void setupSqlResult(List<Object[]> result) {
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(query).setParameter(Mockito.anyInt(), Mockito.any());
        Mockito.doReturn(result).when(query).getResultList();
        Mockito.doReturn(query).when(em).createNativeQuery(Mockito.any());
    }

    @Test
    public void testGetBorrowingUC() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "", "1"},
                new Object[]{"UCI", "Library B", "Library C", "Copy non returnable", "Courier", "2"},
                new Object[]{"UCI", "Library C", "Library D", "Copy non returnable", "Email", "3"},
                new Object[]{"UCI", "Library D", "Library E", "Loan", "FTP", "4"},
                new Object[]{"UCI", "Library E", "Library A", "Loan", "Postal", "5"}
        ));
        Assert.assertEquals(
                Sets.newSet(
                        new VdxBorrowingByCategory(VdxCampus.Irvine, "Library A", "Library B", VdxServiceType.CopyNonReturnable, VdxShipDeliveryMethod.Other, 1L),
                        new VdxBorrowingByCategory(VdxCampus.Irvine, "Library B", "Library C", VdxServiceType.CopyNonReturnable, VdxShipDeliveryMethod.Courier, 2L),
                        new VdxBorrowingByCategory(VdxCampus.Irvine, "Library C", "Library D", VdxServiceType.CopyNonReturnable, VdxShipDeliveryMethod.Email, 3L),
                        new VdxBorrowingByCategory(VdxCampus.Irvine, "Library D", "Library E", VdxServiceType.Loan, VdxShipDeliveryMethod.FTP, 4L),
                        new VdxBorrowingByCategory(VdxCampus.Irvine, "Library E", "Library A", VdxServiceType.Loan, VdxShipDeliveryMethod.Postal, 5L)
                ),
                repo.getBorrowingUC(null, null, null).collect(Collectors.toSet())
        );
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUCWhenProcedureGivesWrongOutputs() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUCWhenBorrowingLibraryNameIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "", "1"},
                new Object[]{"UCI", null, "Library A", "Copy non returnable", "", "2"}
        ));
        repo.getBorrowingUC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUCWhenLendingLibraryNameIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "", "1"},
                new Object[]{"UCI", "Library B", null, "Copy non returnable", "", "2"}
        ));
        repo.getBorrowingUC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUCWhenCampusIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "", "1"},
                new Object[]{null, "Library B", "Library A", "Copy non returnable", "", "2"}
        ));
        repo.getBorrowingUC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUCWhenServiceTypeIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "", "1"},
                new Object[]{"UCI", "Library B", "Library A", null, "", "2"}
        ));
        repo.getBorrowingUC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUCWhenDeliveryMethodIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "", "1"},
                new Object[]{"UCI", "Library B", "Library A", "Copy non returnable", null, "2"}
        ));
        repo.getBorrowingUC(null, null, null).collect(Collectors.toList());
    }

    @Test
    public void testGetBorrowingUCWhenCampusIsBlank() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "", "1"},
                new Object[]{"", "Library B", "Library A", "Copy non returnable", "", "2"}
        ));
        Assert.assertEquals(
                Sets.newSet(
                        new VdxBorrowingByCategory(VdxCampus.Irvine, "Library A", "Library B", VdxServiceType.CopyNonReturnable, VdxShipDeliveryMethod.Other, 1L),
                        new VdxBorrowingByCategory(VdxCampus.None, "Library B", "Library A", VdxServiceType.CopyNonReturnable, VdxShipDeliveryMethod.Other, 2L)
                ),
                repo.getBorrowingUC(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUCWhenCampusIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "", "1"},
                new Object[]{"UCZ", "Library B", "Library A", "Copy non returnable", "", "2"}
        ));
        repo.getBorrowingUC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUCWhenServiceTypeIsBlank() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "", "", "1"},
                new Object[]{"UCI", "Library B", "Library A", "", "", "2"}
        ));
        repo.getBorrowingUC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUCWhenServiceTypeIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Replication", "", "1"},
                new Object[]{"UCI", "Library B", "Library A", "Replication", "", "2"}
        ));
        repo.getBorrowingUC(null, null, null).collect(Collectors.toList());
    }

    @Test
    public void testGetBorrowingUCWhenDeliveryMethodIsBlank() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Loan", "", "1"},
                new Object[]{"UCI", "Library B", "Library A", "Loan", "", "2"}
        ));
        Assert.assertEquals(
                Sets.newSet(
                        new VdxBorrowingByCategory(VdxCampus.Irvine, "Library A", "Library B", VdxServiceType.Loan, VdxShipDeliveryMethod.Other, 1L),
                        new VdxBorrowingByCategory(VdxCampus.Irvine, "Library B", "Library A", VdxServiceType.Loan, VdxShipDeliveryMethod.Other, 2L)
                ),
                repo.getBorrowingUC(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUCWhenDeliveryMethodIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Loan", "", "1"},
                new Object[]{"UCI", "Library B", "Library A", "Loan", "Carrier pigeon", "2"}
        ));
        repo.getBorrowingUC(null, null, null).collect(Collectors.toList());
    }

}