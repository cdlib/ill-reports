package org.cdlib.ill.report.vdx.procedures;

import java.util.Arrays;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.vdx.VdxBorrowerCategory;
import org.cdlib.ill.report.vdx.VdxCampus;
import static org.cdlib.ill.report.vdx.procedures.EntityManagerMockHelper.stubNativeQueryResultList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpVdxJournalBorrowingRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private SpVdxJournalBorrowingRepository repo;

    @Test
    public void testGetJournalBorrowing() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"UCLA", "Library B", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "2"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxJournalBorrowing(VdxCampus.LosAngeles, "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", VdxBorrowerCategory.GraduateStudent, 1L),
                new SpVdxJournalBorrowing(VdxCampus.LosAngeles, "Library B", "Some Title", "1999", "Volume 1", "pp 100-199", VdxBorrowerCategory.GraduateStudent, 2L)
        ), repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJournalBorrowingWhenProcedureGivesWrongOutput() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"UCLA", "Library B", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "2", "extra"}
        ));
        repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet());
    }

    @Test
    public void testGetJournalBorrowingWhenCampusIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"", "Library B", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "2"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxJournalBorrowing(VdxCampus.LosAngeles, "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", VdxBorrowerCategory.GraduateStudent, 1L),
                new SpVdxJournalBorrowing(VdxCampus.None, "Library B", "Some Title", "1999", "Volume 1", "pp 100-199", VdxBorrowerCategory.GraduateStudent, 2L)
        ), repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJournalBorrowingWhenCampusIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"UCZ", "Library B", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "2"}
        ));
        repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJournalBorrowingWhenCampusIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{null, "Library B", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "2"}
        ));
        repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJournalBorrowingWhenBorrowingLibraryIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"UCLA", null, "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "2"}
        ));
        repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJournalBorrowingWhenTitleIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"UCLA", "Library B", null, "1999", "Volume 1", "pp 100-199", "Grad", "2"}
        ));
        repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJournalBorrowingWhenPublicationYearIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"UCLA", "Library B", "Some Title", null, "Volume 1", "pp 100-199", "Grad", "2"}
        ));
        repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJournalBorrowingWhenRequestIssueTitleIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"UCLA", "Library B", "Some Title", "1999", null, "pp 100-199", "Grad", "2"}
        ));
        repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJournalBorrowingWhenPaginationIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"UCLA", "Library B", "Spme Title", "1999", "Volume 1", null, "Grad", "2"}
        ));
        repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetJournalBorrowingWhenPatronCategoryIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"UCLA", "Library B", "Some Title", "1999", "Volume 1", "pp 100-199", null, "2"}
        ));
        repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJournalBorrowingWhenPatronCategoryIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"UCLA", "Library B", "Some Title", "1999", "Volume 1", "pp 100-199", "", "2"}
        ));
        repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetJournalBorrowingWhenPatronCategoryIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCLA", "Library A", "Some Title", "1999", "Volume 1", "pp 100-199", "Grad", "1"},
                new Object[]{"UCLA", "Library B", "Some Title", "1999", "Volume 1", "pp 100-199", "Dumbledore", "2"}
        ));
        repo.getJournalBorrowing(null, null, null).collect(Collectors.toSet());
    }
    
}
