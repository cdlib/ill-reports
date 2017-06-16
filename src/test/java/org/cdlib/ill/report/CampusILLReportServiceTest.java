package org.cdlib.ill.report;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import org.cdlib.ill.model.CampusILLReport;
import org.cdlib.ill.report.vdx.VdxBorrowingRepository;
import org.cdlib.ill.report.vdx.VdxBorrowingSummary;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxCategory;
import org.cdlib.ill.report.vdx.VdxLendingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CampusILLReportServiceTest {

    @Mock
    private VdxBorrowingRepository vdxBorrowingRepo;
    @Mock
    private VdxLendingRepository vdxLendingRepo;
    @InjectMocks
    private CampusILLReportService service;

    private final String LIBRARY_A = "Library A";
    private final String LIBRARY_B = "Library B";

    @Test
    public void testGetCampusILLReportAggregatesCategoricalBorrowingByLibrary() {
        Mockito.doReturn(Arrays.stream(
                new VdxBorrowingSummary[]{
                    new VdxBorrowingSummary(VdxCampus.Davis, LIBRARY_A, VdxCategory.UC, 1L),
                    new VdxBorrowingSummary(VdxCampus.Davis, LIBRARY_A, VdxCategory.OCLC, 2L),
                    new VdxBorrowingSummary(VdxCampus.Davis, LIBRARY_B, VdxCategory.UC, 4L)
                }))
                .when(vdxBorrowingRepo)
                .getBorrowingSummary(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doReturn(Stream.empty())
                .when(vdxLendingRepo)
                .getLendingSummary(Mockito.any(), Mockito.any(), Mockito.any());
        CampusILLReport report = service.getILLCampusReport(null, null, null);

        Assert.assertTrue(report.getInstitutionReports().stream().anyMatch(library -> {
            return LIBRARY_A.equals(library.getName()) && Objects.equals(3L, library.getTotalBorrowing());
        }));
        Assert.assertTrue(report.getInstitutionReports().stream().anyMatch(library -> {
            return LIBRARY_B.equals(library.getName()) && Objects.equals(4L, library.getTotalBorrowing());
        }));
    }

}
