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
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", "Library C", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library C", "UC Library", "Library D", "3", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library D", "UC Library", "Library E", "4", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library E", "UC Library", "Library A", "5", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxLendingUnfilledDetail(VdxCampus.Irvine, "Library A", "UC Library", "Library B", 1L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", VdxServiceType.Loan),
                new SpVdxLendingUnfilledDetail(VdxCampus.Irvine, "Library B", "UC Library", "Library C", 2L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", VdxServiceType.Loan),
                new SpVdxLendingUnfilledDetail(VdxCampus.Irvine, "Library C", "UC Library", "Library D", 3L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", VdxServiceType.Loan),
                new SpVdxLendingUnfilledDetail(VdxCampus.Irvine, "Library D", "UC Library", "Library E", 4L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", VdxServiceType.Loan),
                new SpVdxLendingUnfilledDetail(VdxCampus.Irvine, "Library E", "UC Library", "Library A", 5L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", VdxServiceType.Loan)
        ), repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenProcedureGivesWrongOutputs() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan", "extra"},
                new Object[]{"UCI", "Library B", "UC Library", "Library A", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenCampusIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{null, "Library B", "UC Library", "Library A", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }

    @Test
    public void testGetLendingUnfilledDetailWhenCampusIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"", "Library B", "UC Library", "Library C", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxLendingUnfilledDetail(VdxCampus.Irvine, "Library A", "UC Library", "Library B", 1L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", VdxServiceType.Loan),
                new SpVdxLendingUnfilledDetail(VdxCampus.None, "Library B", "UC Library", "Library C", 2L, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", VdxServiceType.Loan)
        ), repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenCampusIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCZ", "Library B", "UC Library", "Library A", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenBorrowingLibraryNameIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", null, "UC Library", "Library A", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenBorrowingLibraryTypeIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", null, "Library A", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenLendingLibraryNameIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", null, "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenIllnoIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", "Library A", null, "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenTitleIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", "Library A", "2", null, "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenPublicationDateIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", "Library A", "2", "The Title", null, "MIT Press", "on the shelf", "laziness", "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenPublisherIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", "Library A", "2", "The Title", "1999", null, "on the shelf", "laziness", "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenShelfIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", "Library A", "2", "The Title", "1999", "MIT Press", null, "laziness", "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenAnswerIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", "Library A", "2", "The Title", "1999", "MIT Press", "on the shelf", null, "Serial", "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenMaterialTypeIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", "Library A", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", null, "Loan"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenServiceTypeIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", "Library A", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", null}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenServiceTypeIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", "Library A", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", ""}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLendingUnfilledDetailWhenServiceTypeIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Library A", "UC Library", "Library B", "1", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Loan"},
                new Object[]{"UCI", "Library B", "UC Library", "Library A", "2", "The Title", "1999", "MIT Press", "on the shelf", "laziness", "Serial", "Telepathy"}
        ));
        repo.getLendingUnfilledDetail(null, null, null).collect(Collectors.toSet());
    }
}
