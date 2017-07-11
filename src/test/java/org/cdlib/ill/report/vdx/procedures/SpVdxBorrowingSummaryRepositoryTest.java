package org.cdlib.ill.report.vdx.procedures;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpVdxBorrowingSummaryRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private SpVdxBorrowingSummaryRepository repo;

    private void setupSqlResult(List<Object[]> result) {
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(query).setParameter(Mockito.anyInt(), Mockito.any());
        Mockito.doReturn(result).when(query).getResultList();
        Mockito.doReturn(query).when(em).createNativeQuery(Mockito.any());
    }

    @Test
    public void testGetBorrowingSummary() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "", "1"},
                new Object[]{"UCD", "Library B", "I", "2"},
                new Object[]{"UCLA", "Library C", "U", "3"}
        ));
        Assert.assertEquals(Sets.newSet(new SpVdxBorrowingSummary(VdxCampus.Berkeley, "Library A", VdxCategory.OCLC, 1L),
                        new SpVdxBorrowingSummary(VdxCampus.Davis, "Library B", VdxCategory.ISOPartners, 2L),
                        new SpVdxBorrowingSummary(VdxCampus.LosAngeles, "Library C", VdxCategory.UC, 3L)
                ),
                repo.getBorrowingSummary(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingSummaryWhenLibraryNameIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCB", null, "", "1"}
        ));
        repo.getBorrowingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingSummaryWhenCampusIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{null, "Some Library", "", "1"}
        ));
        repo.getBorrowingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test
    public void testGetBorrowingSummaryWhenCampusIsBlank() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"", "Some Library", "", "1"}
        ));
        Assert.assertEquals(Sets.newSet(new SpVdxBorrowingSummary(VdxCampus.Berkeley, "Some Library", VdxCategory.OCLC, 1L),
                        new SpVdxBorrowingSummary(VdxCampus.None, "Some Library", VdxCategory.OCLC, 1L)
                ),
                repo.getBorrowingSummary(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingSummaryWhenCampusIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCZ", "Some Library", "", "1"}
        ));
        repo.getBorrowingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingSummaryWhenCategoryIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCB", "Some Library", null, "1"}
        ));
        repo.getBorrowingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBorrowingSummaryWhenCategoryIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCB", "Some Library", "Z", "1"}
        ));
        repo.getBorrowingSummary(null, null, null).collect(Collectors.toList());
    }

}
