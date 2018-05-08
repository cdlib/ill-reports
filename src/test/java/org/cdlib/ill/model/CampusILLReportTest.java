package org.cdlib.ill.model;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

public class CampusILLReportTest {

  // Naming order by category:
  // Borrowing: [ISO, OCLC, UC] Lending: [ISO, OCLC, UC]
  private static final InstitutionILLReport A_0_0_0_0_0_0 = new InstitutionILLReport("A", null);
  private static final InstitutionILLReport A_1_0_0_0_0_0 = new InstitutionILLReport("A", null);
  private static final InstitutionILLReport A_0_2_0_0_0_0 = new InstitutionILLReport("A", null);
  private static final InstitutionILLReport A_0_0_4_0_0_0 = new InstitutionILLReport("A", null);
  private static final InstitutionILLReport A_0_0_0_8_0_0 = new InstitutionILLReport("A", null);
  private static final InstitutionILLReport A_0_0_0_0_16_0 = new InstitutionILLReport("A", null);
  private static final InstitutionILLReport A_0_0_0_0_0_32 = new InstitutionILLReport("A", null);

  private static final InstitutionILLReport B_1_0_0_0_0_0 = new InstitutionILLReport("B", null);
  private static final InstitutionILLReport B_0_2_0_0_0_0 = new InstitutionILLReport("B", null);
  private static final InstitutionILLReport B_0_0_4_0_0_0 = new InstitutionILLReport("B", null);
  private static final InstitutionILLReport B_0_0_0_8_0_0 = new InstitutionILLReport("B", null);
  private static final InstitutionILLReport B_0_0_0_0_16_0 = new InstitutionILLReport("B", null);
  private static final InstitutionILLReport B_0_0_0_0_0_32 = new InstitutionILLReport("B", null);

  static {
    A_1_0_0_0_0_0.setTotalISOBorrowing(1L);
    A_0_2_0_0_0_0.setTotalOCLCBorrowing(2L);
    A_0_0_4_0_0_0.setTotalUCBorrowing(4L);
    A_0_0_0_8_0_0.setTotalISOLending(8L);
    A_0_0_0_0_16_0.setTotalOCLCLending(16L);
    A_0_0_0_0_0_32.setTotalUCLending(32L);

    B_1_0_0_0_0_0.setTotalISOBorrowing(1L);
    B_0_2_0_0_0_0.setTotalOCLCBorrowing(2L);
    B_0_0_4_0_0_0.setTotalUCBorrowing(4L);
    B_0_0_0_8_0_0.setTotalISOLending(8L);
    B_0_0_0_0_16_0.setTotalOCLCLending(16L);
    B_0_0_0_0_0_32.setTotalUCLending(32L);
  }

  @Test
  public void testGetTotalISOBorrowing() {
    CampusILLReport report = new CampusILLReport();
    assertEquals(0L, (Object) report.getTotalISOBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_0));
    assertEquals(0L, (Object) report.getTotalISOBorrowing());
    report.setInstitutionReports(Arrays.asList(A_1_0_0_0_0_0));
    assertEquals(1L, (Object) report.getTotalISOBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_2_0_0_0_0));
    assertEquals(0L, (Object) report.getTotalISOBorrowing());
    report.setInstitutionReports(Arrays.asList(A_1_0_0_0_0_0, B_1_0_0_0_0_0));
    assertEquals(2L, (Object) report.getTotalISOBorrowing());
  }

  @Test
  public void testGetTotalISOLending() {
    CampusILLReport report = new CampusILLReport();
    assertEquals(0L, (Object) report.getTotalISOLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_0));
    assertEquals(0L, (Object) report.getTotalISOLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_8_0_0));
    assertEquals(8L, (Object) report.getTotalISOLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_16_0));
    assertEquals(0L, (Object) report.getTotalISOLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_8_0_0, B_0_0_0_8_0_0));
    assertEquals(16L, (Object) report.getTotalISOLending());
  }

  @Test
  public void testGetTotalOCLCBorrowing() {
    CampusILLReport report = new CampusILLReport();
    assertEquals(0L, (Object) report.getTotalOCLCBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_0));
    assertEquals(0L, (Object) report.getTotalOCLCBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_2_0_0_0_0));
    assertEquals(2L, (Object) report.getTotalOCLCBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_0_4_0_0_0));
    assertEquals(0L, (Object) report.getTotalOCLCBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_2_0_0_0_0, B_0_2_0_0_0_0));
    assertEquals(4L, (Object) report.getTotalOCLCBorrowing());
  }

  @Test
  public void testGetTotalOCLCLending() {
    CampusILLReport report = new CampusILLReport();
    assertEquals(0L, (Object) report.getTotalOCLCLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_0));
    assertEquals(0L, (Object) report.getTotalOCLCLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_16_0));
    assertEquals(16L, (Object) report.getTotalOCLCLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_32));
    assertEquals(0L, (Object) report.getTotalOCLCLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_16_0, B_0_0_0_0_16_0));
    assertEquals(32L, (Object) report.getTotalOCLCLending());
  }

  @Test
  public void testGetTotalUCBorrowing() {
    CampusILLReport report = new CampusILLReport();
    assertEquals(0L, (Object) report.getTotalUCBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_0));
    assertEquals(0L, (Object) report.getTotalUCBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_0_4_0_0_0));
    assertEquals(4L, (Object) report.getTotalUCBorrowing());
    report.setInstitutionReports(Arrays.asList(A_1_0_0_0_0_0));
    assertEquals(0L, (Object) report.getTotalUCBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_0_4_0_0_0, B_0_0_4_0_0_0));
    assertEquals(8L, (Object) report.getTotalUCBorrowing());
  }

  @Test
  public void testGetTotalUCLending() {
    CampusILLReport report = new CampusILLReport();
    assertEquals(0L, (Object) report.getTotalUCLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_0));
    assertEquals(0L, (Object) report.getTotalUCLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_32));
    assertEquals(32L, (Object) report.getTotalUCLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_8_0_0));
    assertEquals(0L, (Object) report.getTotalUCLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_32, B_0_0_0_0_0_32));
    assertEquals(64L, (Object) report.getTotalUCLending());
  }

  @Test
  public void testGetTotalBorrowing() {
    CampusILLReport report = new CampusILLReport();
    assertEquals(0L, (Object) report.getTotalBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_0));
    assertEquals(0L, (Object) report.getTotalBorrowing());
    report.setInstitutionReports(Arrays.asList(A_1_0_0_0_0_0));
    assertEquals(1L, (Object) report.getTotalBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_2_0_0_0_0));
    assertEquals(2L, (Object) report.getTotalBorrowing());
    report.setInstitutionReports(Arrays.asList(A_0_0_4_0_0_0));
    assertEquals(4L, (Object) report.getTotalBorrowing());
    report.setInstitutionReports(Arrays.asList(A_1_0_0_0_0_0, A_0_2_0_0_0_0, A_0_0_4_0_0_0));
    assertEquals(7L, (Object) report.getTotalBorrowing());
    report.setInstitutionReports(Arrays.asList(A_1_0_0_0_0_0, B_1_0_0_0_0_0));
    assertEquals(2L, (Object) report.getTotalBorrowing());
    report.setInstitutionReports(Arrays.asList(A_1_0_0_0_0_0, B_0_0_0_8_0_0));
    assertEquals(1L, (Object) report.getTotalBorrowing());
  }

  @Test
  public void testGetTotalLending() {
    CampusILLReport report = new CampusILLReport();
    assertEquals(0L, (Object) report.getTotalLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_0));
    assertEquals(0L, (Object) report.getTotalLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_8_0_0));
    assertEquals(8L, (Object) report.getTotalLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_16_0));
    assertEquals(16L, (Object) report.getTotalLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_0_0_32));
    assertEquals(32L, (Object) report.getTotalLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_8_0_0, A_0_0_0_0_16_0, A_0_0_0_0_0_32));
    assertEquals(56L, (Object) report.getTotalLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_8_0_0, B_0_0_0_8_0_0));
    assertEquals(16L, (Object) report.getTotalLending());
    report.setInstitutionReports(Arrays.asList(A_0_0_0_8_0_0, B_1_0_0_0_0_0));
    assertEquals(8L, (Object) report.getTotalLending());
  }

}
