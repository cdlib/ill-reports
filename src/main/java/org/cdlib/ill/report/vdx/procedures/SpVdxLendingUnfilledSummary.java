package org.cdlib.ill.report.vdx.procedures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxCampusSerializer;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.cdlib.ill.report.vdx.VdxServiceTypeSerializer;

@JsonPropertyOrder({
  "respCampus",
  "respName",
  "reqName",
  "reqLoctype",
  "serviceTp",
  "count"
})
public class SpVdxLendingUnfilledSummary {

  @JsonProperty("Lending Campus")
  @JsonSerialize(using = VdxCampusSerializer.class)
  private VdxCampus respCampus;
  @JsonProperty("Lending Library")
  private String respName;
  @JsonProperty("Borrowing Library Type")
  private String reqLoctype;
  @JsonProperty("Borrowing Library")
  private String reqName;
  @JsonProperty("Loan Service")
  @JsonSerialize(using = VdxServiceTypeSerializer.class)
  private VdxServiceType serviceTp;
  @JsonProperty("Total")
  private Long count;

  public SpVdxLendingUnfilledSummary() {
  }

  public SpVdxLendingUnfilledSummary(VdxCampus respCampus, String respName, String reqLoctype, String reqName, VdxServiceType serviceTp, Long count) {
    this.respCampus = respCampus;
    this.respName = respName;
    this.reqLoctype = reqLoctype;
    this.reqName = reqName;
    this.serviceTp = serviceTp;
    this.count = count;
  }

  public VdxCampus getRespCampus() {
    return respCampus;
  }

  public void setRespCampus(VdxCampus respCampus) {
    this.respCampus = respCampus;
  }

  public String getRespName() {
    return respName;
  }

  public void setRespName(String respName) {
    this.respName = respName;
  }

  public String getReqLoctype() {
    return reqLoctype;
  }

  public void setReqLoctype(String reqLoctype) {
    this.reqLoctype = reqLoctype;
  }

  public String getReqName() {
    return reqName;
  }

  public void setReqName(String reqName) {
    this.reqName = reqName;
  }

  public VdxServiceType getServiceTp() {
    return serviceTp;
  }

  public void setServiceTp(VdxServiceType serviceTp) {
    this.serviceTp = serviceTp;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 37 * hash + Objects.hashCode(this.respCampus);
    hash = 37 * hash + Objects.hashCode(this.respName);
    hash = 37 * hash + Objects.hashCode(this.reqLoctype);
    hash = 37 * hash + Objects.hashCode(this.reqName);
    hash = 37 * hash + Objects.hashCode(this.serviceTp);
    hash = 37 * hash + Objects.hashCode(this.count);
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
    final SpVdxLendingUnfilledSummary other = (SpVdxLendingUnfilledSummary) obj;
    if (!Objects.equals(this.respName, other.respName)) {
      return false;
    }
    if (!Objects.equals(this.reqLoctype, other.reqLoctype)) {
      return false;
    }
    if (!Objects.equals(this.reqName, other.reqName)) {
      return false;
    }
    if (this.respCampus != other.respCampus) {
      return false;
    }
    if (this.serviceTp != other.serviceTp) {
      return false;
    }
    if (!Objects.equals(this.count, other.count)) {
      return false;
    }
    return true;
  }

}
