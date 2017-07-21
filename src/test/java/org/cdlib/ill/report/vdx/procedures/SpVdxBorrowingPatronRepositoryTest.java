package org.cdlib.ill.report.vdx.procedures;

import java.util.Arrays;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.vdx.VdxBorrowerCategory;
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
public class SpVdxBorrowingPatronRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private SpVdxBorrowingPatronRepository repo;

    @Test
    public void testGetBorrowingPatron() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library C", "Loan", "Grad", "2"},
                new Object[]{"UCD", "Library C", "Library A", "Loan", "Grad", "3"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxBorrowingPatron(VdxCampus.Davis, "Library A", "Library B", VdxServiceType.Loan, VdxBorrowerCategory.GraduateStudent, 1L),
                new SpVdxBorrowingPatron(VdxCampus.Davis, "Library B", "Library C", VdxServiceType.Loan, VdxBorrowerCategory.GraduateStudent, 2L),
                new SpVdxBorrowingPatron(VdxCampus.Davis, "Library C", "Library A", VdxServiceType.Loan, VdxBorrowerCategory.GraduateStudent, 3L)
        ), repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test
    public void testGetBorrowingPatronWhenCampusIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"", "Library B", "Library A", "Loan", "Grad", "2"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxBorrowingPatron(VdxCampus.Davis, "Library A", "Library B", VdxServiceType.Loan, VdxBorrowerCategory.GraduateStudent, 1L),
                new SpVdxBorrowingPatron(VdxCampus.None, "Library B", "Library A", VdxServiceType.Loan, VdxBorrowerCategory.GraduateStudent, 2L)
        ), repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingPatronWhenCampusIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{null, "Library B", "Library A", "Loan", "Grad", "2"}
        ));
        repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingPatronWhenCampusIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCZ", "Library B", "Library A", "Loan", "Grad", "2"}
        ));
        repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingPatronWhenBorrowingLibraryIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", null, "Library A", "Loan", "Grad", "2"}
        ));
        repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingPatronWhenLendingLibraryIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", null, "Loan", "Grad", "2"}
        ));
        repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingPatronWhenServiceTypeIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", null, "Grad", "2"}
        ));
        repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingPatronWhenServiceTypeIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", "", "Grad", "2"}
        ));
        repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingPatronWhenServiceTypeIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", "Telepathy", "Grad", "2"}
        ));
        repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet());
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingPatronWhenPatronCategoryIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", "Loan", null, "2"}
        ));
        repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingPatronWhenPatronCategoryIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", "Loan", "", "2"}
        ));
        repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingPatronWhenPatronCategoryIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", "Loan", "Maester", "2"}
        ));
        repo.getBorrowingPatron(null, null, null).collect(Collectors.toSet());
    }
}
