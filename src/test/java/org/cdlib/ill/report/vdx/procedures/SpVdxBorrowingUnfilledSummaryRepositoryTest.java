package org.cdlib.ill.report.vdx.procedures;

import java.util.Arrays;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxServiceType;
import static org.cdlib.ill.report.vdx.procedures.EntityManagerMockHelper.stubNativeQueryResultList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpVdxBorrowingUnfilledSummaryRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private SpVdxBorrowingUnfilledSummaryRepository repo;

    @Test
    public void testGetBorrowingUnfilledSummary() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCSB", "Library A", "Library B", "Loan", "1"},
                new Object[]{"UCSB", "Library B", "Library A", "Copy non returnable", "2"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxBorrowingUnfilledSummary(VdxCampus.SantaBarbara, "Library A", "Library B", VdxServiceType.Loan, 1L),
                new SpVdxBorrowingUnfilledSummary(VdxCampus.SantaBarbara, "Library B", "Library A", VdxServiceType.CopyNonReturnable, 2L)
        ), repo.getBorrowingUnfilledSummary(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUnfilledSummaryWhenProcedureGivesWrongOutputs() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCSB", "Library A", "Library B", "Loan", "1", "oops"},
                new Object[]{"UCSB", "Library B", "Library A", "Copy non returnable", "2", "oops"}
        ));
        repo.getBorrowingUnfilledSummary(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUnfilledSummaryWhenCampusIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCSB", "Library A", "Library B", "Loan", "1"},
                new Object[]{null, "Library B", "Library A", "Copy non returnable", "2"}
        ));
        repo.getBorrowingUnfilledSummary(null, null, null).collect(Collectors.toSet());
    }

    @Test
    public void testGetBorrowingUnfilledSummaryWhenCampusIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCSB", "Library A", "Library B", "Loan", "1"},
                new Object[]{"", "Library B", "Library A", "Copy non returnable", "2"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxBorrowingUnfilledSummary(VdxCampus.SantaBarbara, "Library A", "Library B", VdxServiceType.Loan, 1L),
                new SpVdxBorrowingUnfilledSummary(VdxCampus.None, "Library B", "Library A", VdxServiceType.CopyNonReturnable, 2L)
        ), repo.getBorrowingUnfilledSummary(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUnfilledSummaryWhenCampusIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCSB", "Library A", "Library B", "Loan", "1"},
                new Object[]{"UCZ", "Library B", "Library A", "Copy non returnable", "2"}
        ));
        repo.getBorrowingUnfilledSummary(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUnfilledSummaryWhenBorrowingLibraryNameIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCSB", "Library A", "Library B", "Loan", "1"},
                new Object[]{"UCSB", null, "Library A", "Copy non returnable", "2"}
        ));
        repo.getBorrowingUnfilledSummary(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUnfilledSummaryWhenLendingLibraryNameIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCSB", "Library A", "Library B", "Loan", "1"},
                new Object[]{"UCSB", "Library B", null, "Copy non returnable", "2"}
        ));
        repo.getBorrowingUnfilledSummary(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUnfilledSummaryWhenServiceTypeIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCSB", "Library A", "Library B", "Loan", "1"},
                new Object[]{"UCSB", "Library B", "Library A", null, "2"}
        ));
        repo.getBorrowingUnfilledSummary(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUnfilledSummaryWhenServiceTypeIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCSB", "Library A", "Library B", "Loan", "1"},
                new Object[]{"UCSB", "Library B", "Library A", "", "2"}
        ));
        repo.getBorrowingUnfilledSummary(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingUnfilledSummaryWhenServiceTypeIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCSB", "Library A", "Library B", "Loan", "1"},
                new Object[]{"UCSB", "Library B", "Library A", "Clone", "2"}
        ));
        repo.getBorrowingUnfilledSummary(null, null, null).collect(Collectors.toSet());
    }
}
