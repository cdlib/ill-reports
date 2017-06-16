package org.cdlib.ill.model;

public class InstitutionILLReport {

    private String name;
    private String campus;
    private Long totalISOBorrowing = 0L;
    private Long totalOCLCBorrowing = 0L;
    private Long totalUCBorrowing = 0L;
    private Long totalISOLending = 0L;
    private Long totalOCLCLending = 0L;
    private Long totalUCLending = 0L;

    public InstitutionILLReport() {
    }

    public InstitutionILLReport(String name, String campus) {
        this.name = name;
        this.campus = campus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public Long getTotalISOBorrowing() {
        return totalISOBorrowing;
    }

    public void setTotalISOBorrowing(Long totalISOBorrowing) {
        this.totalISOBorrowing = totalISOBorrowing;
    }

    public Long getTotalOCLCBorrowing() {
        return totalOCLCBorrowing;
    }

    public void setTotalOCLCBorrowing(Long totalOCLCBorrowing) {
        this.totalOCLCBorrowing = totalOCLCBorrowing;
    }

    public Long getTotalUCBorrowing() {
        return totalUCBorrowing;
    }

    public void setTotalUCBorrowing(Long totalUCBorrowing) {
        this.totalUCBorrowing = totalUCBorrowing;
    }

    public Long getTotalISOLending() {
        return totalISOLending;
    }

    public void setTotalISOLending(Long totalISOLending) {
        this.totalISOLending = totalISOLending;
    }

    public Long getTotalOCLCLending() {
        return totalOCLCLending;
    }

    public void setTotalOCLCLending(Long totalOCLCLending) {
        this.totalOCLCLending = totalOCLCLending;
    }

    public Long getTotalUCLending() {
        return totalUCLending;
    }

    public void setTotalUCLending(Long totalUCLending) {
        this.totalUCLending = totalUCLending;
    }

    public Long getTotalBorrowing() {
        return this.totalISOBorrowing + this.totalOCLCBorrowing + this.totalUCBorrowing;
    }

    public Long getTotalLending() {
        return this.totalISOLending + this.totalOCLCLending + this.totalUCLending;
    }
}
