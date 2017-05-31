package org.cdlib.ill.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author mmorrisp
 */
public class CampusReport {

    private String campusCode;
    private List<InstitutionReport> institutions;
    private LocalDate reportBeginDate;
    private LocalDate reportEndDate;

    public CampusReport() {
    }

    public CampusReport(String code) {
        this.campusCode = code;
        this.institutions = new ArrayList<>();
    }

    public String getCampusCode() {
        return campusCode;
    }

    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    public List<InstitutionReport> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<InstitutionReport> institutions) {
        this.institutions = institutions;
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
        return institutions.stream().collect(Collectors.summingLong(InstitutionReport::getTotalISOBorrowing));
    }

    public Long getTotalISOLending() {
        return institutions.stream().collect(Collectors.summingLong(InstitutionReport::getTotalISOLending));
    }

    public Long getTotalOCLCBorrowing() {
        return institutions.stream().collect(Collectors.summingLong(InstitutionReport::getTotalOCLCBorrowing));
    }

    public Long getTotalOCLCLending() {
        return institutions.stream().collect(Collectors.summingLong(InstitutionReport::getTotalOCLCLending));
    }

    public Long getTotalUCBorrowing() {
        return institutions.stream().collect(Collectors.summingLong(InstitutionReport::getTotalUCBorrowing));
    }

    public Long getTotalUCLending() {
        return institutions.stream().collect(Collectors.summingLong(InstitutionReport::getTotalUCLending));
    }

    public Long getTotalBorrowing() {
        return institutions.stream().collect(Collectors.summingLong(InstitutionReport::getTotalBorrowing));
    }

    public Long getTotalLending() {
        return institutions.stream().collect(Collectors.summingLong(InstitutionReport::getTotalLending));
    }
}
