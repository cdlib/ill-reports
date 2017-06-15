package org.cdlib.ill.report.vdx;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * The lending summary logic is in MySQL stored procedures. This repository is
 * responsible for converting the output of the stored procedure into the domain
 * model, {@link VdxLendingSummary}.
 *
 * This conversion might fail if the database produces unexpected campus codes
 * or categories.
 *
 * @author mmorrisp
 */
@RunWith(MockitoJUnitRunner.class)
public class VdxLendingRepositoryTest {
    
    @Mock
    private EntityManager em;
    @InjectMocks
    private VdxLendingRepository repo;

    private void setupSqlResult(List<Object[]> result) {
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(query).setParameter(Mockito.anyInt(), Mockito.any());
        Mockito.doReturn(result).when(query).getResultList();
        Mockito.doReturn(query).when(em).createNativeQuery(Mockito.any());
    }

    @Test
    public void testGetLendingSummary() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Library A", "", "1"},
                new Object[]{"UCD", "Library B", "I", "2"},
                new Object[]{"UCLA", "Library C", "U", "3"}
        ));
        Assert.assertEquals(
                Sets.newSet(
                        new VdxLendingSummary(VdxCampus.Berkeley, "Library A", VdxCategory.OCLC, 1L),
                        new VdxLendingSummary(VdxCampus.Davis, "Library B", VdxCategory.ISOPartners, 2L),
                        new VdxLendingSummary(VdxCampus.LosAngeles, "Library C", VdxCategory.UC, 3L)
                ),
                repo.getLendingSummary(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingSummaryWhenLibraryNameIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCB", null, "", "1"}
        ));
        repo.getLendingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingSummaryWhenCampusIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{null, "Some Library", "", "1"}
        ));
        repo.getLendingSummary(null, null, null).collect(Collectors.toList());
    }
    @Test
    public void testGetLendingSummaryWhenCampusIsBlank() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"", "Some Library", "", "1"}
        ));
        Assert.assertEquals(
                Sets.newSet(
                        new VdxLendingSummary(VdxCampus.Berkeley, "Some Library", VdxCategory.OCLC, 1L),
                        new VdxLendingSummary(VdxCampus.None, "Some Library", VdxCategory.OCLC, 1L)
                ),
                repo.getLendingSummary(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingSummaryWhenCampusIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCZ", "Some Library", "", "1"}
        ));
        repo.getLendingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingSummaryWhenCategoryIsNull() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCB", "Some Library", null, "1"}
        ));
        repo.getLendingSummary(null, null, null).collect(Collectors.toList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingSummaryWhenCategoryIsNew() {
        setupSqlResult(Arrays.asList(
                new Object[]{"UCB", "Some Library", "", "1"},
                new Object[]{"UCB", "Some Library", "Z", "1"}
        ));
        repo.getLendingSummary(null, null, null).collect(Collectors.toList());
    }
    
}
