package org.cdlib.ill.report.vdx;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mmorrisp
 */
@Entity
@Table(name = "vdx_lending")
public class VdxLending implements Serializable {

    @Id
    @Column(nullable = false)
    private Long illno;

    @Column(name = "rec_date", nullable = true)
    private LocalDate recDate;

    @Column(name = "entry_date", nullable = false)
    private LocalDateTime entryDate;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "req_name", nullable = false))
        ,
        @AttributeOverride(name = "campus", column = @Column(name = "req_campus", nullable = false))
        ,
        @AttributeOverride(name = "category", column = @Column(name = "req_category", nullable = false))
    })
    private VdxHolder borrower;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "resp_name", nullable = false))
        ,
        @AttributeOverride(name = "campus", column = @Column(name = "resp_campus", nullable = false))
        ,
        @AttributeOverride(name = "category", column = @Column(name = "resp_category", nullable = false))
    })
    private VdxHolder lender;

    public Long getIllno() {
        return illno;
    }

    public void setIllno(Long illno) {
        this.illno = illno;
    }

    public LocalDate getRecDate() {
        return recDate;
    }

    public void setRecDate(LocalDate recDate) {
        this.recDate = recDate;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public VdxHolder getBorrower() {
        return borrower;
    }

    public void setBorrower(VdxHolder borrower) {
        this.borrower = borrower;
    }

    public VdxHolder getLender() {
        return lender;
    }

    public void setLender(VdxHolder lender) {
        this.lender = lender;
    }

}
