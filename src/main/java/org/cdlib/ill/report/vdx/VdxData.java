package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.cdlib.ill.report.api.PreferredLocalDateFormatSerializer;
import org.cdlib.ill.report.api.PreferredLocalDateTimeFormatSerializer;

@MappedSuperclass
public abstract class VdxData {

    @Id
    @Column(nullable = false)
    protected Long illno;

    @JsonProperty("rec_date")
    @Column(name = "rec_date", nullable = true)
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate recDate;

    @JsonProperty("entry_date")
    @Column(name = "entry_date", nullable = false)
    @JsonSerialize(using = PreferredLocalDateTimeFormatSerializer.class)
    private LocalDateTime entryDate;

    @JsonProperty("need_date")
    @Column(name = "need_date", nullable = true)
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate needDate;

    @JsonProperty("expiry_date")
    @Column(name = "expiry_date", nullable = true)
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate expiryDate;

    @JsonProperty("date_shipped")
    @Column(name = "date_shipped", nullable = true)
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate dateShipped;

    @Column(name = "role", nullable = false)
    private String role;

    @JsonUnwrapped(prefix = "req_")
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "req_name", nullable = false))
        ,
        @AttributeOverride(name = "symbol", column = @Column(name = "req_symbol", nullable = false))
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
        @AttributeOverride(name = "symbol", column = @Column(name = "resp_symbol", nullable = false))
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

    public LocalDate getNeedDate() {
        return needDate;
    }

    public void setNeedDate(LocalDate needDate) {
        this.needDate = needDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(LocalDate dateShipped) {
        this.dateShipped = dateShipped;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.illno);
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
        final VdxData other = (VdxData) obj;
        if (!Objects.equals(this.illno, other.illno)) {
            return false;
        }
        return true;
    }

}
