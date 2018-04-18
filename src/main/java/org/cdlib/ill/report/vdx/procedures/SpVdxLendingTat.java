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
  "tat",
  "serviceTp",
  "shipDeliveryMethod",
  "count"
})
public class SpVdxLendingTat {

  @JsonProperty("lending campus")
  @JsonSerialize(using = VdxCampusSerializer.class)
  private VdxCampus respCampus;
  @JsonProperty("lending library")
  private String respName;
  @JsonProperty("days")
  private Long tat;
  @JsonProperty("loan service")
  @JsonSerialize(using = VdxServiceTypeSerializer.class)
  private VdxServiceType serviceTp;
  @JsonProperty("delivery method")
  @JsonSerialize(using = VdxShipDeliveryMethodSerializer.class)
  private VdxShipDeliveryMethod shipDeliveryMethod;
  @JsonProperty("total")
  private Long count;

  public SpVdxLendingTat() {
  }

  public SpVdxLendingTat(VdxCampus respCampus, String respName, Long tat, VdxServiceType serviceTp, VdxShipDeliveryMethod shipDeliveryMethod, Long count) {
    this.respCampus = respCampus;
    this.respName = respName;
    this.tat = tat;
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

  public Long getTat() {
    return tat;
  }

  public void setTat(Long tat) {
    this.tat = tat;
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
    hash = 97 * hash + Objects.hashCode(this.respCampus);
    hash = 97 * hash + Objects.hashCode(this.respName);
    hash = 97 * hash + Objects.hashCode(this.tat);
    hash = 97 * hash + Objects.hashCode(this.serviceTp);
    hash = 97 * hash + Objects.hashCode(this.shipDeliveryMethod);
    hash = 97 * hash + Objects.hashCode(this.count);
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
    final SpVdxLendingTat other = (SpVdxLendingTat) obj;
    if (!Objects.equals(this.respName, other.respName)) {
      return false;
    }
    if (this.respCampus != other.respCampus) {
      return false;
    }
    if (!Objects.equals(this.tat, other.tat)) {
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
