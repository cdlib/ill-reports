package org.cdlib.ill.report.vdx.procedures;

import java.util.Arrays;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxILLCategory;
import static org.cdlib.ill.report.vdx.procedures.EntityManagerMockHelper.stubNativeQueryResultList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * The lending summary logic is in MySQL stored procedures. This repository is
 * responsible for converting the output of the stored procedure into the domain
 * model, {@link SpVdxLendingSummary}.
 *
 * This conversion might fail if the database produces unexpected campus codes
 * or categories.
 *
 * @author mmorrisp
 */
@RunWith(MockitoJUnitRunner.class)
public class SpVdxLendingSummaryRepositoryTest {

  @Mock
  private EntityManager em;
  @InjectMocks
  private SpVdxLendingSummaryRepository repo;

  @Test
  public void testGetLendingSummary() {
    stubNativeQueryResultList(em, Arrays.asList(
        new Object[]{"UCB", "Library A", "", "1"},
        new Object[]{"UCD", "Library B", "I", "2"},
        new Object[]{"UCLA", "Library C", "U", "3"}
    ));
    Assert.assertEquals(Sets.newSet(new SpVdxLendingSummary(VdxCampus.Berkeley, "Library A", VdxILLCategory.OCLC, 1L),
        new SpVdxLendingSummary(VdxCampus.Davis, "Library B", VdxILLCategory.ISOPartners, 2L),
        new SpVdxLendingSummary(VdxCampus.LosAngeles, "Library C", VdxILLCategory.UC, 3L)
    ),
        repo.getLendingSummary(null, null, null).collect(Collectors.toSet())
    );
  }

  @Test
  public void testGetLendingSummaryWhenCampusIsBlank() {
    stubNativeQueryResultList(em, Arrays.asList(
        new Object[]{"UCB", "Some Library", "", "1"},
        new Object[]{"", "Some Library", "", "1"}
    ));
    Assert.assertEquals(Sets.newSet(new SpVdxLendingSummary(VdxCampus.Berkeley, "Some Library", VdxILLCategory.OCLC, 1L),
        new SpVdxLendingSummary(VdxCampus.None, "Some Library", VdxILLCategory.OCLC, 1L)
    ),
        repo.getLendingSummary(null, null, null).collect(Collectors.toSet())
    );
  }

}
