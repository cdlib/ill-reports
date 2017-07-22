package org.cdlib.ill.report.vdx.procedures;

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
import static org.cdlib.ill.report.vdx.procedures.EntityManagerMockHelper.stubNativeQueryResultList;

@RunWith(MockitoJUnitRunner.class)
public class SpVdxBorrowingSummaryRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private SpVdxBorrowingSummaryRepository repo;

    @Test
    public void testGetBorrowingSummary() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCB", "Library A", "", "1"},
                new Object[]{"UCD", "Library B", "I", "2"},
                new Object[]{"UCLA", "Library C", "U", "3"}
        ));
        Assert.assertEquals(Sets.newSet(new SpVdxBorrowingSummary(VdxCampus.Berkeley, "Library A", VdxILLCategory.OCLC, 1L),
                new SpVdxBorrowingSummary(VdxCampus.Davis, "Library B", VdxILLCategory.ISOPartners, 2L),
                new SpVdxBorrowingSummary(VdxCampus.LosAngeles, "Library C", VdxILLCategory.UC, 3L)
        ),
                repo.getBorrowingSummary(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingSummaryWhenProcedureGivesWrongOutput() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCB", "Some Library", "", "1", "extra"}
        ));
        repo.getBorrowingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingSummaryWhenLibraryNameIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCB", null, "", "1"}
        ));
        repo.getBorrowingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingSummaryWhenCampusIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{null, "Some Library", "", "1"}
        ));
        repo.getBorrowingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test
    public void testGetBorrowingSummaryWhenCampusIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"", "Some Library", "", "1"}
        ));
        Assert.assertEquals(Sets.newSet(new SpVdxBorrowingSummary(VdxCampus.Berkeley, "Some Library", VdxILLCategory.OCLC, 1L),
                new SpVdxBorrowingSummary(VdxCampus.None, "Some Library", VdxILLCategory.OCLC, 1L)
        ),
                repo.getBorrowingSummary(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingSummaryWhenCampusIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCZ", "Some Library", "", "1"}
        ));
        repo.getBorrowingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingSummaryWhenCategoryIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCB", "Some Library", null, "1"}
        ));
        repo.getBorrowingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingSummaryWhenCategoryIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCB", "Some Library", "Z", "1"}
        ));
        repo.getBorrowingSummary(null, null, null).collect(Collectors.toList());
    }

}
