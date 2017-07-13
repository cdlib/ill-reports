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
public class SpVdxBorrowingOCLCRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private SpVdxBorrowingOCLCRepository repo;

    private void setupSqlResult(List<Object[]> result) {
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(query).setParameter(Mockito.anyInt(), Mockito.any());
        Mockito.doReturn(result).when(query).getResultList();
        Mockito.doReturn(query).when(em).createNativeQuery(Mockito.any());
    }

    @Test
    public void testGetBorrowingOCLC() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "1"},
                new Object[]{"UCI", "Library B", "Library C", "Copy non returnable", "2"},
                new Object[]{"UCI", "Library C", "Library D", "Copy non returnable", "3"},
                new Object[]{"UCI", "Library D", "Library E", "Loan", "4"},
                new Object[]{"UCI", "Library E", "Library A", "Loan", "5"}
        ));
        Assert.assertEquals(Sets.newSet(new SpVdxBorrowingByCategory(VdxCampus.Irvine, "Library A", "Library B", VdxServiceType.CopyNonReturnable, null, 1L),
                new SpVdxBorrowingByCategory(VdxCampus.Irvine, "Library B", "Library C", VdxServiceType.CopyNonReturnable, null, 2L),
                new SpVdxBorrowingByCategory(VdxCampus.Irvine, "Library C", "Library D", VdxServiceType.CopyNonReturnable, null, 3L),
                new SpVdxBorrowingByCategory(VdxCampus.Irvine, "Library D", "Library E", VdxServiceType.Loan, null, 4L),
                new SpVdxBorrowingByCategory(VdxCampus.Irvine, "Library E", "Library A", VdxServiceType.Loan, null, 5L)),
                repo.getBorrowingOCLC(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingOCLCWhenProcedureGivesWrongOutputs() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "1", "extra"},
                new Object[]{"UCI", "Library B", "Library C", "Copy non returnable", "2", "extra"}
        ));
        repo.getBorrowingOCLC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingOCLCWhenBorrowingLibraryNameIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "1"},
                new Object[]{"UCI", null, "Library A", "Copy non returnable", "2"}
        ));
        repo.getBorrowingOCLC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingOCLCWhenLendingLibraryNameIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "1"},
                new Object[]{"UCI", "Library B", null, "Copy non returnable", "2"}
        ));
        repo.getBorrowingOCLC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingOCLCWhenCampusIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "1"},
                new Object[]{null, "Library B", "Library A", "Copy non returnable", "2"}
        ));
        repo.getBorrowingOCLC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingOCLCWhenServiceTypeIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "1"},
                new Object[]{"UCI", "Library B", "Library A", null, "2"}
        ));
        repo.getBorrowingOCLC(null, null, null).collect(Collectors.toList());
    }

    @Test
    public void testGetBorrowingOCLCWhenCampusIsBlank() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "1"},
                new Object[]{"", "Library B", "Library A", "Copy non returnable", "2"}
        ));
        Assert.assertEquals(Sets.newSet(new SpVdxBorrowingByCategory(VdxCampus.Irvine, "Library A", "Library B", VdxServiceType.CopyNonReturnable, null, 1L),
                new SpVdxBorrowingByCategory(VdxCampus.None, "Library B", "Library A", VdxServiceType.CopyNonReturnable, null, 2L)
        ),
                repo.getBorrowingOCLC(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingOCLCWhenCampusIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Copy non returnable", "1"},
                new Object[]{"UCZ", "Library B", "Library A", "Copy non returnable", "2"}
        ));
        repo.getBorrowingOCLC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingOCLCWhenServiceTypeIsBlank() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "", "1"},
                new Object[]{"UCI", "Library B", "Library A", "", "2"}
        ));
        repo.getBorrowingOCLC(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingOCLCWhenServiceTypeIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCI", "Library A", "Library B", "Replication", "1"},
                new Object[]{"UCI", "Library B", "Library A", "Replication", "2"}
        ));
        repo.getBorrowingOCLC(null, null, null).collect(Collectors.toList());
    }
}
