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
    @Column(name = "illno", nullable = false)
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

    @JsonProperty("due_date")
    @Column(name = "due_date", nullable = true)
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate dueDate;

    @JsonProperty("local_due_date")
    @Column(name = "local_due_date", nullable = true)
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate localDueDate;

    @JsonProperty("answer_date")
    @Column(name = "answer_date", nullable = true)
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate answerDate;

    @JsonProperty("return_date")
    @Column(name = "return_date", nullable = true)
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate returnDate;

    @JsonProperty("checkin_date")
    @Column(name = "checkin_date", nullable = true)
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate checkinDate;

    @JsonProperty("termination_date")
    @Column(name = "termination_date", nullable = true)
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate terminationDate;

    @Column(name = "role", nullable = false)
    private String role;

    @JsonProperty("servicetp")
    @Column(name = "servicetp", nullable = false)
    @JsonSerialize(using = VdxServiceTypeSerializer.class)
    private VdxServiceType servicetp;

    @JsonProperty("req_servicetp")
    @Column(name = "req_servicetp", nullable = false)
    @JsonSerialize(using = VdxServiceTypeSerializer.class)
    private VdxServiceType reqServicetp;

    @JsonProperty("ship_delivery_method")
    @Column(name = "ship_delivery_method", nullable = false)
    @JsonSerialize(using = VdxShipDeliveryMethodSerializer.class)
    private VdxShipDeliveryMethod shipDeliveryMethod;

    @JsonProperty("ship_delivery_method_text")
    @Column(name = "ship_delivery_method_text", nullable = false)
    private String shipDeliveryMethodText;

    @JsonProperty("req_delivery_method")
    @Column(name = "req_delivery_method", nullable = false)
    @JsonSerialize(using = VdxShipDeliveryMethodSerializer.class)
    private VdxShipDeliveryMethod reqDeliveryMethod;

    @JsonProperty("req_delivery_method_text")
    @Column(name = "req_delivery_method_text", nullable = false)
    private String reqDeliveryMethodText;

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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getLocalDueDate() {
        return localDueDate;
    }

    public void setLocalDueDate(LocalDate localDueDate) {
        this.localDueDate = localDueDate;
    }

    public LocalDate getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(LocalDate answerDate) {
        this.answerDate = answerDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public VdxServiceType getServicetp() {
        return servicetp;
    }

    public void setServicetp(VdxServiceType servicetp) {
        this.servicetp = servicetp;
    }

    public VdxServiceType getReqServicetp() {
        return reqServicetp;
    }

    public void setReqServicetp(VdxServiceType reqServicetp) {
        this.reqServicetp = reqServicetp;
    }

    public VdxShipDeliveryMethod getShipDeliveryMethod() {
        return shipDeliveryMethod;
    }

    public void setShipDeliveryMethod(VdxShipDeliveryMethod shipDeliveryMethod) {
        this.shipDeliveryMethod = shipDeliveryMethod;
    }

    public String getShipDeliveryMethodText() {
        return shipDeliveryMethodText;
    }

    public void setShipDeliveryMethodText(String shipDeliveryMethodText) {
        this.shipDeliveryMethodText = shipDeliveryMethodText;
    }

    public VdxShipDeliveryMethod getReqDeliveryMethod() {
        return reqDeliveryMethod;
    }

    public void setReqDeliveryMethod(VdxShipDeliveryMethod reqDeliveryMethod) {
        this.reqDeliveryMethod = reqDeliveryMethod;
    }

    public String getReqDeliveryMethodText() {
        return reqDeliveryMethodText;
    }

    public void setReqDeliveryMethodText(String reqDeliveryMethodText) {
        this.reqDeliveryMethodText = reqDeliveryMethodText;
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
