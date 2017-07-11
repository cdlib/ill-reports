package org.cdlib.ill.report.vdx.procedures;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.cdlib.ill.report.vdx.VdxShipDeliveryMethod;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpVdxLendingRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private SpVdxLendingRepository repo;

    private void setupSqlResult(List<Object[]> result) {
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(query).setParameter(Mockito.anyInt(), Mockito.any());
        Mockito.doReturn(result).when(query).getResultList();
        Mockito.doReturn(query).when(em).createNativeQuery(Mockito.any());
    }

    @Test
    public void testGetLending() {
        setupSqlResult(Arrays.asList(
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

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingWhenProcedureGivesWrongOutputs() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1", "extra"},
                new Object[]{"UCB", "Library B", "UC Library", "Library C", "Loan", "Courier", "2", "extra"}
        ));
        repo.getLending(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingWhenBorrowingLibraryNameIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
                new Object[]{"UCB", null, "UC Library", "Library C", "Loan", "Courier", "2"}
        ));
        repo.getLending(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingWhenLendingLibraryNameIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
                new Object[]{"UCB", "Library B", "UC Library", null, "Loan", "Courier", "2"}
        ));
        repo.getLending(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingWhenCampusIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
                new Object[]{null, "Library B", "UC Library", "Library C", "Loan", "Courier", "2"}
        ));
        repo.getLending(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingWhenServiceTypeIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
                new Object[]{"UCB", "Library B", "UC Library", "Library C", null, "Courier", "2"}
        ));
        repo.getLending(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingWhenDeliveryMethodIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
                new Object[]{"UCB", "Library B", "UC Library", "Library C", "Loan", null, "2"}
        ));
        repo.getLending(null, null, null).collect(Collectors.toSet());
    }

    @Test
    public void testGetLendingWhenCampusIsBlank() {
        setupSqlResult(Arrays.asList(
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

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingWhenCampusIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
                new Object[]{"UCZ", "Library B", "UC Library", "Library C", "Loan", "Courier", "2"}
        ));
        repo.getLending(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingWhenServiceTypeIsBlank() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
                new Object[]{"UCB", "Library B", "UC Library", "Library C", "", "Courier", "2"}
        ));
        repo.getLending(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingWhenServiceTypeIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
                new Object[]{"UCB", "Library B", "UC Library", "Library C", "Replication", "Courier", "2"}
        ));
        repo.getLending(null, null, null).collect(Collectors.toSet());
    }

    @Test
    public void testGetLendingWhenDeliveryMethodIsBlank() {
        setupSqlResult(Arrays.asList(
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

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingWhenDeliveryMethodIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "UC Library", "Library B", "Loan", "Courier", "1"},
                new Object[]{"UCB", "Library B", "UC Library", "Library C", "Loan", "Drone", "2"}
        ));
        repo.getLending(null, null, null).collect(Collectors.toSet());
    }
}
