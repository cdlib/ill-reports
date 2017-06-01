package org.cdlib.ill.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.ResourceSupport;

/**
 * 
 * @author mmorrisp
 */
public class CampusReport extends ResourceSupport {

    private String campusCode;
    private List<InstitutionReport> institutionReports;
    private LocalDate reportBeginDate;
    private LocalDate reportEndDate;

    public CampusReport() {
        this.institutionReports = new ArrayList<>();
    }

    public CampusReport(String code) {
        this.campusCode = code;
        this.institutionReports = new ArrayList<>();
    }

    public String getCampusCode() {
        return campusCode;
    }

    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    public List<InstitutionReport> getInstitutionReports() {
        return institutionReports;
    }

    public void setInstitutionReports(List<InstitutionReport> institutionReports) {
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
        return institutionReports.stream().collect(Collectors.summingLong(InstitutionReport::getTotalISOBorrowing));
    }

    public Long getTotalISOLending() {
        return institutionReports.stream().collect(Collectors.summingLong(InstitutionReport::getTotalISOLending));
    }

    public Long getTotalOCLCBorrowing() {
        return institutionReports.stream().collect(Collectors.summingLong(InstitutionReport::getTotalOCLCBorrowing));
    }

    public Long getTotalOCLCLending() {
        return institutionReports.stream().collect(Collectors.summingLong(InstitutionReport::getTotalOCLCLending));
    }

    public Long getTotalUCBorrowing() {
        return institutionReports.stream().collect(Collectors.summingLong(InstitutionReport::getTotalUCBorrowing));
    }

    public Long getTotalUCLending() {
        return institutionReports.stream().collect(Collectors.summingLong(InstitutionReport::getTotalUCLending));
    }

    public Long getTotalBorrowing() {
        return institutionReports.stream().collect(Collectors.summingLong(InstitutionReport::getTotalBorrowing));
    }

    public Long getTotalLending() {
        return institutionReports.stream().collect(Collectors.summingLong(InstitutionReport::getTotalLending));
    }
}
