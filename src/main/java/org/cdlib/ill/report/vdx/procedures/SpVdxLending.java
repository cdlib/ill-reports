package org.cdlib.ill.report.vdx.procedures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxCampusSerializer;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.cdlib.ill.report.vdx.VdxServiceTypeSerializer;
import org.cdlib.ill.report.vdx.VdxShipDeliveryMethod;
import org.cdlib.ill.report.vdx.VdxShipDeliveryMethodSerializer;

@JsonPropertyOrder({
  "respCampus",
  "respName",
  "reqLocType",
  "reqName",
  "serviceTp",
  "shipDeliveryMethod",
  "count"
})
public class SpVdxLending {

  @JsonProperty("lending campus")
  @JsonSerialize(using = VdxCampusSerializer.class)
  private VdxCampus respCampus;
  @JsonProperty("lending library")
  private String respName;
  @JsonProperty("borrower's location type")
  private String reqLocType;
  @JsonProperty("borrowing library")
  private String reqName;
  @JsonProperty("loan service")
  @JsonSerialize(using = VdxServiceTypeSerializer.class)
  private VdxServiceType serviceTp;
  @JsonProperty("delivery method")
  @JsonSerialize(using = VdxShipDeliveryMethodSerializer.class)
  private VdxShipDeliveryMethod shipDeliveryMethod;
  @JsonProperty("total")
  private Long count;

  public SpVdxLending() {
  }

  public SpVdxLending(VdxCampus respCampus, String respName, String reqLocType, String reqName, VdxServiceType serviceTp, VdxShipDeliveryMethod shipDeliveryMethod, Long count) {
    this.respCampus = respCampus;
    this.respName = respName;
    this.reqLocType = reqLocType;
    this.reqName = reqName;
    this.serviceTp = serviceTp;
    this.shipDeliveryMethod = shipDeliveryMethod;
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

  public String getReqLocType() {
    return reqLocType;
  }

  public void setReqLocType(String reqLocType) {
    this.reqLocType = reqLocType;
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

  public VdxShipDeliveryMethod getShipDeliveryMethod() {
    return shipDeliveryMethod;
  }

  public void setShipDeliveryMethod(VdxShipDeliveryMethod shipDeliveryMethod) {
    this.shipDeliveryMethod = shipDeliveryMethod;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 17 * hash + Objects.hashCode(this.respCampus);
    hash = 17 * hash + Objects.hashCode(this.respName);
    hash = 17 * hash + Objects.hashCode(this.reqLocType);
    hash = 17 * hash + Objects.hashCode(this.reqName);
    hash = 17 * hash + Objects.hashCode(this.serviceTp);
    hash = 17 * hash + Objects.hashCode(this.shipDeliveryMethod);
    hash = 17 * hash + Objects.hashCode(this.count);
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
    final SpVdxLending other = (SpVdxLending) obj;
    if (!Objects.equals(this.respName, other.respName)) {
      return false;
    }
    if (!Objects.equals(this.reqLocType, other.reqLocType)) {
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
    if (this.shipDeliveryMethod != other.shipDeliveryMethod) {
      return false;
    }
    if (!Objects.equals(this.count, other.count)) {
      return false;
    }
    return true;
  }

}
