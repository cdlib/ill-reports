package org.cdlib.ill.report.vdx.schedule_c;

import java.util.Objects;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.cdlib.ill.report.vdx.VdxShipDeliveryMethod;

public class LendingSubtotal {

  private VdxCampus respCampus;
  private String respName;
  private String reqLocType;
  private VdxCampus reqCampus;
  private String reqName;
  private VdxServiceType serviceTp;
  private VdxShipDeliveryMethod shipDeliveryMethod;
  private Long count;

  public LendingSubtotal() {
  }

  public LendingSubtotal(VdxCampus respCampus, String respName, String reqLocType, VdxCampus reqCampus, String reqName, VdxServiceType serviceTp, VdxShipDeliveryMethod shipDeliveryMethod, Long count) {
    this.respCampus = respCampus;
    this.respName = respName;
    this.reqLocType = reqLocType;
    this.reqCampus = reqCampus;
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

  public VdxCampus getReqCampus() {
    return reqCampus;
  }

  public void setReqCampus(VdxCampus reqCampus) {
    this.reqCampus = reqCampus;
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
    int hash = 7;
    hash = 83 * hash + Objects.hashCode(this.respCampus);
    hash = 83 * hash + Objects.hashCode(this.respName);
    hash = 83 * hash + Objects.hashCode(this.reqLocType);
    hash = 83 * hash + Objects.hashCode(this.reqCampus);
    hash = 83 * hash + Objects.hashCode(this.reqName);
    hash = 83 * hash + Objects.hashCode(this.serviceTp);
    hash = 83 * hash + Objects.hashCode(this.shipDeliveryMethod);
    hash = 83 * hash + Objects.hashCode(this.count);
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
    final LendingSubtotal other = (LendingSubtotal) obj;
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
    if (this.reqCampus != other.reqCampus) {
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
