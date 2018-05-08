package org.cdlib.ill.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.ResourceSupport;

public class CampusILLReport extends ResourceSupport {

  private String title;
  private String campusCode;
  private List<InstitutionILLReport> institutionReports;
  private LocalDate reportBeginDate;
  private LocalDate reportEndDate;

  public CampusILLReport() {
    this.institutionReports = new ArrayList<>();
  }

  public CampusILLReport(String title, String campusCode) {
    this.title = title;
    this.campusCode = campusCode;
    this.institutionReports = new ArrayList<>();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCampusCode() {
    return campusCode;
  }

  public void setCampusCode(String campusCode) {
    this.campusCode = campusCode;
  }

  public List<InstitutionILLReport> getInstitutionReports() {
    return institutionReports;
  }

  public void setInstitutionReports(List<InstitutionILLReport> institutionReports) {
    this.institutionReports = institutionReports;
  }

  public LocalDate getReportBeginDate() {
    return reportBeginDate;
  }

  public void setReportBeginDate(LocalDate reportBeginDate) {
    this.reportBeginDate = reportBeginDate;
  }

  public LocalDate getReportEndDate() {
    return reportEndDate;
  }

  public void setReportEndDate(LocalDate reportEndDate) {
    this.reportEndDate = reportEndDate;
  }

  public Long getTotalISOBorrowing() {
    return institutionReports.stream().collect(Collectors.summingLong(InstitutionILLReport::getTotalISOBorrowing));
  }

  public Long getTotalISOLending() {
    return institutionReports.stream().collect(Collectors.summingLong(InstitutionILLReport::getTotalISOLending));
  }

  public Long getTotalOCLCBorrowing() {
    return institutionReports.stream().collect(Collectors.summingLong(InstitutionILLReport::getTotalOCLCBorrowing));
  }

  public Long getTotalOCLCLending() {
    return institutionReports.stream().collect(Collectors.summingLong(InstitutionILLReport::getTotalOCLCLending));
  }

  public Long getTotalUCBorrowing() {
    return institutionReports.stream().collect(Collectors.summingLong(InstitutionILLReport::getTotalUCBorrowing));
  }

  public Long getTotalUCLending() {
    return institutionReports.stream().collect(Collectors.summingLong(InstitutionILLReport::getTotalUCLending));
  }

  public Long getTotalBorrowing() {
    return institutionReports.stream().collect(Collectors.summingLong(InstitutionILLReport::getTotalBorrowing));
  }

  public Long getTotalLending() {
    return institutionReports.stream().collect(Collectors.summingLong(InstitutionILLReport::getTotalLending));
  }
}
