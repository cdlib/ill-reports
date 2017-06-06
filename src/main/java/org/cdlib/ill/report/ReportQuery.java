package org.cdlib.ill.report;

import java.time.LocalDate;

/**
 *
 * @author mmorrisp
 */
public class ReportQuery {

    private String campus;
    private LocalDate from;
    private LocalDate to;

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

}
