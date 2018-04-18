package org.cdlib.ill.report.vdx.schedule_c;

import java.util.Objects;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.cdlib.ill.report.vdx.VdxShipDeliveryMethod;

public class UCBorrowingSubtotal {

  private VdxCampus reqCampus;
  private String reqName;
  private VdxCampus respCampus;
  private String respName;
  private VdxServiceType serviceTp;
  private VdxShipDeliveryMethod shipDeliveryMethod;
  private Long count;

  public UCBorrowingSubtotal() {
  }

  public UCBorrowingSubtotal(VdxCampus reqCampus, String reqName, VdxCampus respCampus, String respName, VdxServiceType serviceTp, VdxShipDeliveryMethod shipDeliveryMethod, Long count) {
    this.reqCampus = reqCampus;
    this.reqName = reqName;
    this.respCampus = respCampus;
    this.respName = respName;
    this.serviceTp = serviceTp;
    this.shipDeliveryMethod = shipDeliveryMethod;
    this.count = count;
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
    hash = 43 * hash + Objects.hashCode(this.reqCampus);
    hash = 43 * hash + Objects.hashCode(this.reqName);
    hash = 43 * hash + Objects.hashCode(this.respCampus);
    hash = 43 * hash + Objects.hashCode(this.respName);
    hash = 43 * hash + Objects.hashCode(this.serviceTp);
    hash = 43 * hash + Objects.hashCode(this.shipDeliveryMethod);
    hash = 43 * hash + Objects.hashCode(this.count);
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
    final UCBorrowingSubtotal other = (UCBorrowingSubtotal) obj;
    if (!Objects.equals(this.reqName, other.reqName)) {
      return false;
    }
    if (!Objects.equals(this.respName, other.respName)) {
      return false;
    }
    if (this.reqCampus != other.reqCampus) {
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
