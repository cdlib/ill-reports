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
    
}
