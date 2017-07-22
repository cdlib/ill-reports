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
public class SpVdxLendingPatronRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private SpVdxLendingPatronRepository repo;
    
    @Test
    public void testGetLendingPatron() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCM", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCM", "Library B", "Library C", "Loan", "Grad", "2"},
                new Object[]{"UCM", "Library C", "Library A", "Loan", "Grad", "3"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxLendingPatron(VdxCampus.Merced, "Library A", "Library B", VdxServiceType.Loan, VdxBorrowerCategory.GraduateStudent, 1L),
                new SpVdxLendingPatron(VdxCampus.Merced, "Library B", "Library C", VdxServiceType.Loan, VdxBorrowerCategory.GraduateStudent, 2L),
                new SpVdxLendingPatron(VdxCampus.Merced, "Library C", "Library A", VdxServiceType.Loan, VdxBorrowerCategory.GraduateStudent, 3L)
        ), repo.getLendingPatron(null, null, null).collect(Collectors.toSet())
        );
    }
    
    @Test
    public void testGetLendingPatronWhenCampusIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"", "Library B", "Library A", "Loan", "Grad", "2"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxLendingPatron(VdxCampus.Davis, "Library A", "Library B", VdxServiceType.Loan, VdxBorrowerCategory.GraduateStudent, 1L),
                new SpVdxLendingPatron(VdxCampus.None, "Library B", "Library A", VdxServiceType.Loan, VdxBorrowerCategory.GraduateStudent, 2L)
        ), repo.getLendingPatron(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingPatronWhenCampusIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{null, "Library B", "Library A", "Loan", "Grad", "2"}
        ));
        repo.getLendingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingPatronWhenCampusIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCZ", "Library B", "Library A", "Loan", "Grad", "2"}
        ));
        repo.getLendingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingPatronWhenLendingLibraryIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", null, "Library A", "Loan", "Grad", "2"}
        ));
        repo.getLendingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingPatronWhenBorrowingLibraryIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", null, "Loan", "Grad", "2"}
        ));
        repo.getLendingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingPatronWhenServiceTypeIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", null, "Grad", "2"}
        ));
        repo.getLendingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingPatronWhenServiceTypeIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", "", "Grad", "2"}
        ));
        repo.getLendingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingPatronWhenServiceTypeIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", "Telepathy", "Grad", "2"}
        ));
        repo.getLendingPatron(null, null, null).collect(Collectors.toSet());
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void testGetLendingPatronWhenPatronCategoryIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", "Loan", null, "2"}
        ));
        repo.getLendingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingPatronWhenPatronCategoryIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", "Loan", "", "2"}
        ));
        repo.getLendingPatron(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingPatronWhenPatronCategoryIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCD", "Library A", "Library B", "Loan", "Grad", "1"},
                new Object[]{"UCD", "Library B", "Library A", "Loan", "Maester", "2"}
        ));
        repo.getLendingPatron(null, null, null).collect(Collectors.toSet());
    }
}
