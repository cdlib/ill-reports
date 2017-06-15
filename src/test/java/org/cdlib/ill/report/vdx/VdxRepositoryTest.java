package org.cdlib.ill.report.vdx;

import java.time.LocalDate;
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

/**
 * The borrowing and lending summary logic is in MySQL stored procedures. This
 * repository is responsible for converting the output of the stored procedure
 * into the domain model,
 * {@link VdxBorrowingSummary}, {@link VdxLendingSummary}.
 *
 * This conversion might fail if the database produces unexpected campus codes
 * or categories.
 *
 * @author mmorrisp
 */
@RunWith(MockitoJUnitRunner.class)
public class VdxRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private VdxRepository repo;

    private static final List EXPECTED_SQL_RESULT = Arrays.asList(
            new String[]{"UCB", "Library A", "", "1"},
            new String[]{"UCD", "Library B", "I", "2"},
            new String[]{"UCLA", "Library C", "U", "3"}
    );

    @Test
    public void testGetBorrowingSummary() {
        // given
        Query query = Mockito.mock(Query.class);
        Mockito.doReturn(query).when(query).setParameter(Mockito.anyInt(), Mockito.any());
        Mockito.doReturn(EXPECTED_SQL_RESULT).when(query).getResultList();
        Mockito.doReturn(query).when(em).createNativeQuery(Mockito.any());

        // expect
        Assert.assertEquals(
                Sets.newSet(
                        new VdxBorrowingSummary(VdxCampus.Berkeley, "Library A", VdxCategory.OCLC, 1L),
                        new VdxBorrowingSummary(VdxCampus.Davis, "Library B", VdxCategory.ISOPartners, 2L),
                        new VdxBorrowingSummary(VdxCampus.LosAngeles, "Library C", VdxCategory.UC, 3L)
                ),
                repo.getBorrowingSummary("UCLA", LocalDate.MIN, LocalDate.MAX).collect(Collectors.toSet())
        );
    }
    
    @Ignore
    @Test(expected = NullPointerException.class)
    public void testGetBorrowingSummaryWhenLibraryNameIsNull() {
    }
    
    @Ignore
    @Test(expected = RuntimeException.class)
    public void testGetBorrowingSummaryWhenCampusIsUnexpected() {
        // null or novel.
    }
    
    @Ignore
    @Test(expected = RuntimeException.class)
    public void testGetBorrowingSummaryWhenCategoryIsUnexpected() {
        // null or novel.
    }

    @Ignore
    @Test
    public void testGetLendingSummary() {
    }

}
