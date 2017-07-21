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
public class SpVdxLendingUnfilledDataRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private SpVdxLendingUnfilledDetailRepository repo;

    @Test
    public void testGetLendingUnfilledDetail() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", "Loan"},
                new Object[]{"UCI", "Library B", "", "Library C", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", "Loan"},
                new Object[]{"UCI", "Library C", "", "Library D", "3", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", "Loan"},
                new Object[]{"UCI", "Library D", "", "Library E", "4", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", "Loan"},
                new Object[]{"UCI", "Library E", "", "Library A", "5", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", "Loan"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxLendingUnfilledDetail(VdxCampus.Irvine, "Library A", "", "Library B", 1L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", VdxServiceType.Loan),
                new SpVdxLendingUnfilledDetail(VdxCampus.Irvine, "Library B", "", "Library C", 2L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", VdxServiceType.Loan),
                new SpVdxLendingUnfilledDetail(VdxCampus.Irvine, "Library C", "", "Library D", 3L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", VdxServiceType.Loan),
                new SpVdxLendingUnfilledDetail(VdxCampus.Irvine, "Library D", "", "Library E", 4L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", VdxServiceType.Loan),
                new SpVdxLendingUnfilledDetail(VdxCampus.Irvine, "Library E", "", "Library A", 5L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", VdxServiceType.Loan)
        ), repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenProcedureGivesWrongOutputs() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", "Loan", "extra"},
                new Object[]{"UCI", "Library B", "", "Library A", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }
    
}
