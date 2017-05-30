package org.cdlib.ill.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Within the UC system, all borrowing/lending institutions can be identified
 * with a campus or regional library facility.
 *
 * @author mmorrisp
 */
public class Campus {

    private String code;
    private List<Institution> institutions;

    public Campus() {
    }

    public Campus(String code) {
        this.code = code;
        this.institutions = new ArrayList<>();
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<Institution> institutions) {
        this.institutions = institutions;
    }

    public Long getTotalISOBorrowing() {
        return institutions.stream().collect(Collectors.summingLong(Institution::getTotalISOBorrowing));
    }

    public Long getTotalISOLending() {
        return institutions.stream().collect(Collectors.summingLong(Institution::getTotalISOLending));
    }

    public Long getTotalOCLCBorrowing() {
        return institutions.stream().collect(Collectors.summingLong(Institution::getTotalOCLCBorrowing));
    }

    public Long getTotalOCLCLending() {
        return institutions.stream().collect(Collectors.summingLong(Institution::getTotalOCLCLending));
    }

    public Long getTotalUCBorrowing() {
        return institutions.stream().collect(Collectors.summingLong(Institution::getTotalUCBorrowing));
    }

    public Long getTotalUCLending() {
        return institutions.stream().collect(Collectors.summingLong(Institution::getTotalUCLending));
    }

    public Long getTotalBorrowing() {
        return institutions.stream().collect(Collectors.summingLong(Institution::getTotalBorrowing));
    }

    public Long getTotalLending() {
        return institutions.stream().collect(Collectors.summingLong(Institution::getTotalLending));
    }
}
