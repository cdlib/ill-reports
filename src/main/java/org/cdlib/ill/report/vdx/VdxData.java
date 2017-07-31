package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonProperty("illno")
    protected Long illno;

    @Column(name = "rec_date", nullable = true)
    @JsonProperty("rec_date")
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate recDate;

    @Column(name = "entry_date", nullable = false)
    @JsonProperty("entry_date")
    @JsonSerialize(using = PreferredLocalDateTimeFormatSerializer.class)
    private LocalDateTime entryDate;

    @Column(name = "need_date", nullable = true)
    @JsonProperty("need_date")
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate needDate;

    @Column(name = "expiry_date", nullable = true)
    @JsonProperty("expiry_date")
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate expiryDate;

    @Column(name = "date_shipped", nullable = true)
    @JsonProperty("date_shipped")
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate dateShipped;

    @Column(name = "due_date", nullable = true)
    @JsonProperty("due_date")
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate dueDate;

    @Column(name = "local_due_date", nullable = true)
    @JsonProperty("local_due_date")
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate localDueDate;

    @Column(name = "answer_date", nullable = true)
    @JsonProperty("answer_date")
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate answerDate;

    @Column(name = "return_date", nullable = true)
    @JsonProperty("return_date")
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate returnDate;

    @Column(name = "checkin_date", nullable = true)
    @JsonProperty("checkin_date")
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate checkinDate;

    @Column(name = "termination_date", nullable = true)
    @JsonProperty("termination_date")
    @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
    private LocalDate terminationDate;

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @Column(name = "auth_status")
    @JsonProperty("auth_status")
    private String authStatus;

    @Column(name = "authorised_by_name")
    @JsonIgnore
    private String authorisedByName;

    @Column(name = "role", nullable = false)
    @JsonProperty("role")
    private String role;

    @Column(name = "servicetp", nullable = false)
    @JsonProperty("servicetp")
    @JsonSerialize(using = VdxServiceTypeSerializer.class)
    private VdxServiceType servicetp;

    @Column(name = "req_servicetp", nullable = false)
    @JsonProperty("req_servicetp")
    @JsonSerialize(using = VdxServiceTypeSerializer.class)
    private VdxServiceType reqServicetp;

    @Column(name = "ship_delivery_method", nullable = false)
    @JsonProperty("ship_delivery_method")
    @JsonSerialize(using = VdxShipDeliveryMethodSerializer.class)
    private VdxShipDeliveryMethod shipDeliveryMethod;

    @Column(name = "ship_delivery_method_text", nullable = false)
    @JsonProperty("ship_delivery_method_text")
    private String shipDeliveryMethodText;

    @Column(name = "req_delivery_method", nullable = false)
    @JsonProperty("req_delivery_method")
    @JsonSerialize(using = VdxShipDeliveryMethodSerializer.class)
    private VdxShipDeliveryMethod reqDeliveryMethod;

    @Column(name = "req_delivery_method_text", nullable = false)
    @JsonProperty("req_delivery_method_text")
    private String reqDeliveryMethodText;

    @Column(name = "borcat", nullable = false)
    @JsonProperty("borcat")
    @JsonSerialize(using = VdxBorrowerCategorySerializer.class)
    private VdxBorrowerCategory borcat;

    @Column(name = "uc_dept", nullable = false)
    @JsonProperty("uc_dept")
    private String ucDept;

    @Column(name = "tgq", nullable = false)
    @JsonProperty("tgq")
    private String tgq;

    @Column(name = "service_level", nullable = false)
    @JsonProperty("service_level")
    private String serviceLevel;

    @Column(name = "req_copyright_subtype", nullable = false)
    @JsonProperty("req_copyright_subtype")
    private String reqCopyrightSubtype;

    @Column(name = "renewable", nullable = false)
    @JsonProperty("renewable")
    private String renewable;

    @Column(name = "pickup_location", nullable = false)
    @JsonProperty("pickup_location")
    private String pickupLocation;

    @Column(name = "material_type", nullable = false)
    @JsonProperty("material_type")
    private String materialType;

    @Column(name = "requester_payment_type", nullable = false)
    @JsonProperty("requester_payment_type")
    private String requesterPaymentType;

    @Column(name = "supplier_reference", nullable = false)
    @JsonProperty("supplier_reference")
    private String supplierReference;

    @Column(name = "supplier_reference_authority", nullable = false)
    @JsonProperty("supplier_reference_authority")
    private String supplierReferenceAuthority;

    @Embedded
    @JsonUnwrapped
    private VdxRequestData requestData;

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
    @JsonUnwrapped(prefix = "req_")
    private VdxHolder borrower;

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
    @JsonUnwrapped(prefix = "resp_")
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getAuthorisedByName() {
        return authorisedByName;
    }

    public void setAuthorisedByName(String authorisedByName) {
        this.authorisedByName = authorisedByName;
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

    public VdxBorrowerCategory getBorcat() {
        return borcat;
    }

    public void setBorcat(VdxBorrowerCategory borcat) {
        this.borcat = borcat;
    }

    public String getUcDept() {
        return ucDept;
    }

    public void setUcDept(String ucDept) {
        this.ucDept = ucDept;
    }

    public String getTgq() {
        return tgq;
    }

    public void setTgq(String tgq) {
        this.tgq = tgq;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getReqCopyrightSubtype() {
        return reqCopyrightSubtype;
    }

    public void setReqCopyrightSubtype(String reqCopyrightSubtype) {
        this.reqCopyrightSubtype = reqCopyrightSubtype;
    }

    public String getRenewable() {
        return renewable;
    }

    public void setRenewable(String renewable) {
        this.renewable = renewable;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getRequesterPaymentType() {
        return requesterPaymentType;
    }

    public void setRequesterPaymentType(String requesterPaymentType) {
        this.requesterPaymentType = requesterPaymentType;
    }

    public String getSupplierReference() {
        return supplierReference;
    }

    public void setSupplierReference(String supplierReference) {
        this.supplierReference = supplierReference;
    }

    public String getSupplierReferenceAuthority() {
        return supplierReferenceAuthority;
    }

    public void setSupplierReferenceAuthority(String supplierReferenceAuthority) {
        this.supplierReferenceAuthority = supplierReferenceAuthority;
    }

    public VdxRequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(VdxRequestData requestData) {
        this.requestData = requestData;
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
