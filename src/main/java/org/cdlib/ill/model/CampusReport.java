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

    private String code;
    private List<InstitutionReport> institutions;
    private LocalDate beginDate;
    private LocalDate endDate;

    public CampusReport() {
    }

    public CampusReport(String code) {
        this.code = code;
        this.institutions = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<InstitutionReport> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<InstitutionReport> institutions) {
        this.institutions = institutions;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
