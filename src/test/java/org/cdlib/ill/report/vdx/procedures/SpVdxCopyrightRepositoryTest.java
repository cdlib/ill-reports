package org.cdlib.ill.report.vdx.procedures;

import java.util.Arrays;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
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
public class SpVdxCopyrightRepositoryTest {

    @Mock
    private EntityManager em;
    @InjectMocks
    private SpVdxCopyrightRepository repo;

    @Test
    public void testGetCopyright() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Some Title", "1999", "1"},
                new Object[]{"UCI", "Some Title", "1999", "2"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxCopyright(VdxCampus.Irvine, "Some Title", "1999", 1L),
                new SpVdxCopyright(VdxCampus.Irvine, "Some Title", "1999", 2L)
        ), repo.getCopyright(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCopyrightWhenProcedureGivesWrongOutputs() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Some Title", "1999", "1"},
                new Object[]{"UCI", "Some Title", "1999", "2", "extra"}
        ));
        repo.getCopyright(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCopyrightWhenCampusIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Some Title", "1999", "1"},
                new Object[]{null, "Some Title", "1999", "2"}
        ));
        repo.getCopyright(null, null, null).collect(Collectors.toSet());
    }

    @Test
    public void testGetCopyrightWhenCampusIsBlank() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Some Title", "1999", "1"},
                new Object[]{"", "Some Title", "1999", "2"}
        ));
        Assert.assertEquals(Sets.newSet(
                new SpVdxCopyright(VdxCampus.Irvine, "Some Title", "1999", 1L),
                new SpVdxCopyright(VdxCampus.None, "Some Title", "1999", 2L)
        ), repo.getCopyright(null, null, null).collect(Collectors.toSet())
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCopyrightWhenCampusIsNew() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Some Title", "1999", "1"},
                new Object[]{"UCZ", "Some Title", "1999", "2"}
        ));
        repo.getCopyright(null, null, null).collect(Collectors.toSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCopyrightWhenTitleIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Some Title", "1999", "1"},
                new Object[]{"UCI", null, "1999", "2"}
        ));
        repo.getCopyright(null, null, null).collect(Collectors.toSet());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetCopyrightWhenPublicationYearIsNull() {
        stubNativeQueryResultList(em, Arrays.asList(
                new Object[]{"UCI", "Some Title", "1999", "1"},
                new Object[]{"UCI", "Some Title", null, "2"}
        ));
        repo.getCopyright(null, null, null).collect(Collectors.toSet());
    }
}
