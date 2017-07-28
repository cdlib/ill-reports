package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.cdlib.ill.report.api.PreferredLocalDateFormatSerializer;
import org.cdlib.ill.report.api.PreferredLocalDateTimeFormatSerializer;

@Entity
@Table(name = "vdx_borrowing")
public class VdxBorrowing implements Serializable {

    @Id
    @Column(nullable = false)
    private Long illno;
    
    @JsonProperty("rec_date")
    @Column(name = "rec_date", nullable = true)
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate recDate;

    @JsonProperty("entry_date")
    @Column(name = "entry_date", nullable = false)
    @JsonSerialize(using = PreferredLocalDateTimeFormatSerializer.class)
    private LocalDateTime entryDate;

    @JsonUnwrapped(prefix = "req_")
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "req_name", nullable = false))
        ,
        @AttributeOverride(name = "campus", column = @Column(name = "req_campus", nullable = false))
        ,
        @AttributeOverride(name = "category", column = @Column(name = "req_category", nullable = false))
    })
    private VdxHolder borrower;

    @JsonUnwrapped(prefix = "resp_")
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.illno ^ (this.illno >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VdxBorrowing other = (VdxBorrowing) obj;
        return Objects.equals(this.illno, other.illno);
    }

}
