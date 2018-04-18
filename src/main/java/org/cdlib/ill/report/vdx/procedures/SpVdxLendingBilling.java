package org.cdlib.ill.report.vdx.procedures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import org.cdlib.ill.report.api.PreferredLocalDateFormatSerializer;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.cdlib.ill.report.vdx.VdxServiceTypeSerializer;

/**
 * This pojo omits the {@code authorised_by_name} and {@code client_name}
 * columns returned by the stored procedure in the interest of protecting the
 * patron's identity.
 *
 * @author mmorrisp
 */
@JsonPropertyOrder({
  "illno",
  "authStatus",
  "baseCost",
  "cost",
  "costExTax",
  "dateShipped",
  "parentIllno",
  "parentTgq",
  "reqArticleAuthor",
  "reqArticleTitle",
  "reqAuthor",
  "reqIdString",
  "reqName",
  "reqLoctype",
  "reqSymbol",
  "reqTitle",
  "requesterPaymentType",
  "respSymbol",
  "respName",
  "reqServicetp",
  "status",
  "supplierReference",
  "tgq",
  "vatCost"
})
public class SpVdxLendingBilling implements Serializable {

  public static final String RESULT_SET_MAPPING = "SpVdxLendingBilling.RESULT_SET_MAPPING";

  @JsonProperty("illno")
  private Long illno;
  @JsonProperty("auth_status")
  private String authStatus;
  @JsonProperty("base_cost")
  private BigDecimal baseCost;
  @JsonProperty("cost")
  private BigDecimal cost;
  @JsonProperty("cost_ex_tax")
  private BigDecimal costExTax;
  @JsonProperty("date_shipped")
  @JsonSerialize(using = PreferredLocalDateFormatSerializer.class)
  private LocalDate dateShipped;
  @JsonProperty("parent_illno")
  private Long parentIllno;
  @JsonProperty("parent_tgq")
  private String parentTgq;
  @JsonProperty("req_article_author")
  private String reqArticleAuthor;
  @JsonProperty("req_article_title")
  private String reqArticleTitle;
  @JsonProperty("req_author")
  private String reqAuthor;
  @JsonProperty("req_id_string")
  private String reqIdString;
  @JsonProperty("req_name")
  private String reqName;
  @JsonProperty("req_loctype")
  private String reqLoctype;
  @JsonProperty("req_symbol")
  private String reqSymbol;
  @JsonProperty("req_title")
  private String reqTitle;
  @JsonProperty("requester_payment_type")
  private String requesterPaymentType;
  @JsonProperty("resp_symbol")
  private String respSymbol;
  @JsonProperty("respName")
  private String respName;
  @JsonProperty("req_servicetp")
  @JsonSerialize(using = VdxServiceTypeSerializer.class)
  private VdxServiceType reqServicetp;
  @JsonProperty("status")
  private String status;
  @JsonProperty("supplier_reference")
  private String supplierReference;
  @JsonProperty("tgq")
  private String tgq;
  @JsonProperty("vat_cost")
  private BigDecimal vatCost;

  public SpVdxLendingBilling() {
  }

  public SpVdxLendingBilling(Long illno, String authStatus, BigDecimal baseCost, BigDecimal cost, BigDecimal costExTax, LocalDate dateShipped, Long parentIllno, String parentTgq, String reqArticleAuthor, String reqArticleTitle, String reqAuthor, String reqIdString, String reqName, String reqLoctype, String reqSymbol, String reqTitle, String requesterPaymentType, String respSymbol, String respName, VdxServiceType reqServicetp, String status, String supplierReference, String tgq, BigDecimal vatCost) {
    this.illno = illno;
    this.authStatus = authStatus;
    this.baseCost = baseCost;
    this.cost = cost;
    this.costExTax = costExTax;
    this.dateShipped = dateShipped;
    this.parentIllno = parentIllno;
    this.parentTgq = parentTgq;
    this.reqArticleAuthor = reqArticleAuthor;
    this.reqArticleTitle = reqArticleTitle;
    this.reqAuthor = reqAuthor;
    this.reqIdString = reqIdString;
    this.reqName = reqName;
    this.reqLoctype = reqLoctype;
    this.reqSymbol = reqSymbol;
    this.reqTitle = reqTitle;
    this.requesterPaymentType = requesterPaymentType;
    this.respSymbol = respSymbol;
    this.respName = respName;
    this.reqServicetp = reqServicetp;
    this.status = status;
    this.supplierReference = supplierReference;
    this.tgq = tgq;
    this.vatCost = vatCost;
  }

  public Long getIllno() {
    return illno;
  }

  public void setIllno(Long illno) {
    this.illno = illno;
  }

  public String getAuthStatus() {
    return authStatus;
  }

  public void setAuthStatus(String authStatus) {
    this.authStatus = authStatus;
  }

  public BigDecimal getBaseCost() {
    return baseCost;
  }

  public void setBaseCost(BigDecimal baseCost) {
    this.baseCost = baseCost;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }

  public BigDecimal getCostExTax() {
    return costExTax;
  }

  public void setCostExTax(BigDecimal costExTax) {
    this.costExTax = costExTax;
  }

  public LocalDate getDateShipped() {
    return dateShipped;
  }

  public void setDateShipped(LocalDate dateShipped) {
    this.dateShipped = dateShipped;
  }

  public Long getParentIllno() {
    return parentIllno;
  }

  public void setParentIllno(Long parentIllno) {
    this.parentIllno = parentIllno;
  }

  public String getParentTgq() {
    return parentTgq;
  }

  public void setParentTgq(String parentTgq) {
    this.parentTgq = parentTgq;
  }

  public String getReqArticleAuthor() {
    return reqArticleAuthor;
  }

  public void setReqArticleAuthor(String reqArticleAuthor) {
    this.reqArticleAuthor = reqArticleAuthor;
  }

  public String getReqArticleTitle() {
    return reqArticleTitle;
  }

  public void setReqArticleTitle(String reqArticleTitle) {
    this.reqArticleTitle = reqArticleTitle;
  }

  public String getReqAuthor() {
    return reqAuthor;
  }

  public void setReqAuthor(String reqAuthor) {
    this.reqAuthor = reqAuthor;
  }

  public String getReqIdString() {
    return reqIdString;
  }

  public void setReqIdString(String reqIdString) {
    this.reqIdString = reqIdString;
  }

  public String getReqName() {
    return reqName;
  }

  public void setReqName(String reqName) {
    this.reqName = reqName;
  }

  public String getReqLoctype() {
    return reqLoctype;
  }

  public void setReqLoctype(String reqLoctype) {
    this.reqLoctype = reqLoctype;
  }

  public String getReqSymbol() {
    return reqSymbol;
  }

  public void setReqSymbol(String reqSymbol) {
    this.reqSymbol = reqSymbol;
  }

  public String getReqTitle() {
    return reqTitle;
  }

  public void setReqTitle(String reqTitle) {
    this.reqTitle = reqTitle;
  }

  public String getRequesterPaymentType() {
    return requesterPaymentType;
  }

  public void setRequesterPaymentType(String requesterPaymentType) {
    this.requesterPaymentType = requesterPaymentType;
  }

  public String getRespSymbol() {
    return respSymbol;
  }

  public void setRespSymbol(String respSymbol) {
    this.respSymbol = respSymbol;
  }

  public String getRespName() {
    return respName;
  }

  public void setRespName(String respName) {
    this.respName = respName;
  }

  public VdxServiceType getReqServicetp() {
    return reqServicetp;
  }

  public void setReqServicetp(VdxServiceType reqServicetp) {
    this.reqServicetp = reqServicetp;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSupplierReference() {
    return supplierReference;
  }

  public void setSupplierReference(String supplierReference) {
    this.supplierReference = supplierReference;
  }

  public String getTgq() {
    return tgq;
  }

  public void setTgq(String tgq) {
    this.tgq = tgq;
  }

  public BigDecimal getVatCost() {
    return vatCost;
  }

  public void setVatCost(BigDecimal vatCost) {
    this.vatCost = vatCost;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 19 * hash + Objects.hashCode(this.illno);
    hash = 19 * hash + Objects.hashCode(this.authStatus);
    hash = 19 * hash + Objects.hashCode(this.baseCost);
    hash = 19 * hash + Objects.hashCode(this.cost);
    hash = 19 * hash + Objects.hashCode(this.costExTax);
    hash = 19 * hash + Objects.hashCode(this.dateShipped);
    hash = 19 * hash + Objects.hashCode(this.parentIllno);
    hash = 19 * hash + Objects.hashCode(this.parentTgq);
    hash = 19 * hash + Objects.hashCode(this.reqArticleAuthor);
    hash = 19 * hash + Objects.hashCode(this.reqArticleTitle);
    hash = 19 * hash + Objects.hashCode(this.reqAuthor);
    hash = 19 * hash + Objects.hashCode(this.reqIdString);
    hash = 19 * hash + Objects.hashCode(this.reqName);
    hash = 19 * hash + Objects.hashCode(this.reqLoctype);
    hash = 19 * hash + Objects.hashCode(this.reqSymbol);
    hash = 19 * hash + Objects.hashCode(this.reqTitle);
    hash = 19 * hash + Objects.hashCode(this.requesterPaymentType);
    hash = 19 * hash + Objects.hashCode(this.respSymbol);
    hash = 19 * hash + Objects.hashCode(this.respName);
    hash = 19 * hash + Objects.hashCode(this.reqServicetp);
    hash = 19 * hash + Objects.hashCode(this.status);
    hash = 19 * hash + Objects.hashCode(this.supplierReference);
    hash = 19 * hash + Objects.hashCode(this.tgq);
    hash = 19 * hash + Objects.hashCode(this.vatCost);
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
    final SpVdxLendingBilling other = (SpVdxLendingBilling) obj;
    if (!Objects.equals(this.authStatus, other.authStatus)) {
      return false;
    }
    if (!Objects.equals(this.parentTgq, other.parentTgq)) {
      return false;
    }
    if (!Objects.equals(this.reqArticleAuthor, other.reqArticleAuthor)) {
      return false;
    }
    if (!Objects.equals(this.reqArticleTitle, other.reqArticleTitle)) {
      return false;
    }
    if (!Objects.equals(this.reqAuthor, other.reqAuthor)) {
      return false;
    }
    if (!Objects.equals(this.reqIdString, other.reqIdString)) {
      return false;
    }
    if (!Objects.equals(this.reqName, other.reqName)) {
      return false;
    }
    if (!Objects.equals(this.reqLoctype, other.reqLoctype)) {
      return false;
    }
    if (!Objects.equals(this.reqSymbol, other.reqSymbol)) {
      return false;
    }
    if (!Objects.equals(this.reqTitle, other.reqTitle)) {
      return false;
    }
    if (!Objects.equals(this.requesterPaymentType, other.requesterPaymentType)) {
      return false;
    }
    if (!Objects.equals(this.respSymbol, other.respSymbol)) {
      return false;
    }
    if (!Objects.equals(this.respName, other.respName)) {
      return false;
    }
    if (!Objects.equals(this.status, other.status)) {
      return false;
    }
    if (!Objects.equals(this.supplierReference, other.supplierReference)) {
      return false;
    }
    if (!Objects.equals(this.tgq, other.tgq)) {
      return false;
    }
    if (!Objects.equals(this.illno, other.illno)) {
      return false;
    }
    if (!Objects.equals(this.baseCost, other.baseCost)) {
      return false;
    }
    if (!Objects.equals(this.cost, other.cost)) {
      return false;
    }
    if (!Objects.equals(this.costExTax, other.costExTax)) {
      return false;
    }
    if (!Objects.equals(this.dateShipped, other.dateShipped)) {
      return false;
    }
    if (!Objects.equals(this.parentIllno, other.parentIllno)) {
      return false;
    }
    if (this.reqServicetp != other.reqServicetp) {
      return false;
    }
    if (!Objects.equals(this.vatCost, other.vatCost)) {
      return false;
    }
    return true;
  }

}
