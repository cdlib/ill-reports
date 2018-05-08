package org.cdlib.ill.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class InstitutionILLReportTest {

  @Test
  public void testGetTotalBorrowing() {
    InstitutionILLReport institution = new InstitutionILLReport();
    assertEquals(0L, (Object) institution.getTotalBorrowing());
    institution.setTotalISOBorrowing(1L);
    assertEquals(1L, (Object) institution.getTotalBorrowing());
    institution.setTotalOCLCBorrowing(2L);
    assertEquals(3L, (Object) institution.getTotalBorrowing());
    institution.setTotalUCBorrowing(3L);
    assertEquals(6L, (Object) institution.getTotalBorrowing());
  }

  @Test
  public void testGetTotalLending() {
    InstitutionILLReport institution = new InstitutionILLReport();
    assertEquals(0L, (Object) institution.getTotalLending());
    institution.setTotalISOLending(1L);
    assertEquals(1L, (Object) institution.getTotalLending());
    institution.setTotalOCLCLending(2L);
    assertEquals(3L, (Object) institution.getTotalLending());
    institution.setTotalUCLending(3L);
    assertEquals(6L, (Object) institution.getTotalLending());
  }

}
