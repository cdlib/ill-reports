package org.cdlib.ill.report.vdx.procedures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxCampusSerializer;
import org.cdlib.ill.report.vdx.VdxILLCategory;
import org.cdlib.ill.report.vdx.VdxILLCategorySerializer;

@JsonPropertyOrder({
  "reqCampus",
  "reqName",
  "respCategory",
  "count"
})
public class SpVdxBorrowingSummary {

  @JsonProperty("borrowing campus")
  @JsonSerialize(using = VdxCampusSerializer.class)
  private VdxCampus reqCampus;
  @JsonProperty("borrowing library")
  private String reqName;
  @JsonProperty("lender category")
  @JsonSerialize(using = VdxILLCategorySerializer.class)
  private VdxILLCategory respCategory;
  @JsonProperty("total")
  private Long count;

  public SpVdxBorrowingSummary() {
  }

  public SpVdxBorrowingSummary(VdxCampus reqCampus, String reqName, VdxILLCategory respCategory, Long count) {
    this.reqCampus = reqCampus;
    this.reqName = reqName;
    this.respCategory = respCategory;
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

  public VdxILLCategory getRespCategory() {
    return respCategory;
  }

  public void setRespCategory(VdxILLCategory respCategory) {
    this.respCategory = respCategory;
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
    hash = 29 * hash + Objects.hashCode(this.reqCampus);
    hash = 29 * hash + Objects.hashCode(this.reqName);
    hash = 29 * hash + Objects.hashCode(this.respCategory);
    hash = 29 * hash + Objects.hashCode(this.count);
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
    final SpVdxBorrowingSummary other = (SpVdxBorrowingSummary) obj;
    if (!Objects.equals(this.reqName, other.reqName)) {
      return false;
    }
    if (this.reqCampus != other.reqCampus) {
      return false;
    }
    if (this.respCategory != other.respCategory) {
      return false;
    }
    if (!Objects.equals(this.count, other.count)) {
      return false;
    }
    return true;
  }

}
