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
  "reqLocType",
  "reqName",
  "illno",
  "reqTitle",
  "reqPublisher",
  "reqPubDate",
  "reqShelf",
  "answerReasonUnfilled",
  "materialType",
  "reqServiceType"
})
public class SpVdxLendingUnfilledDetail {

  @JsonProperty("Lending Campus")
  @JsonSerialize(using = VdxCampusSerializer.class)
  private VdxCampus respCampus;
  @JsonProperty("Lending Library")
  private String respName;
  @JsonProperty("Requesting Library Type")
  private String reqLocType;
  @JsonProperty("Requesting Library")
  private String reqName;
  @JsonProperty("ILL Number")
  private Long illno;
  @JsonProperty("Title")
  private String reqTitle;
  @JsonProperty("Publication Date")
  private String reqPubDate;
  @JsonProperty("Publisher")
  private String reqPublisher;
  @JsonProperty("Call Number")
  private String reqShelf;
  @JsonProperty("Reason Unfilled")
  private String answerReasonUnfilled;
  @JsonProperty("Material Type")
  private String materialType;
  @JsonProperty("Service Type")
  @JsonSerialize(using = VdxServiceTypeSerializer.class)
  private VdxServiceType reqServiceType;

  public SpVdxLendingUnfilledDetail() {
  }

  public SpVdxLendingUnfilledDetail(
      VdxCampus respCampus,
      String respName,
      String reqLocType,
      String reqName,
      Long illno,
      String reqTitle,
      String reqPubDate,
      String reqPublisher,
      String reqShelf,
      String answerReasonUnfilled,
      String materialType,
      VdxServiceType reqServiceType) {
    this.respCampus = respCampus;
    this.respName = respName;
    this.reqLocType = reqLocType;
    this.reqName = reqName;
    this.illno = illno;
    this.reqTitle = reqTitle;
    this.reqPubDate = reqPubDate;
    this.reqPublisher = reqPublisher;
    this.reqShelf = reqShelf;
    this.answerReasonUnfilled = answerReasonUnfilled;
    this.materialType = materialType;
    this.reqServiceType = reqServiceType;
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

  public Long getIllno() {
    return illno;
  }

  public void setIllno(Long illno) {
    this.illno = illno;
  }

  public String getReqTitle() {
    return reqTitle;
  }

  public void setReqTitle(String reqTitle) {
    this.reqTitle = reqTitle;
  }

  public String getReqPubDate() {
    return reqPubDate;
  }

  public void setReqPubDate(String reqPubDate) {
    this.reqPubDate = reqPubDate;
  }

  public String getReqPublisher() {
    return reqPublisher;
  }

  public void setReqPublisher(String reqPublisher) {
    this.reqPublisher = reqPublisher;
  }

  public String getReqShelf() {
    return reqShelf;
  }

  public void setReqShelf(String reqShelf) {
    this.reqShelf = reqShelf;
  }

  public String getAnswerReasonUnfilled() {
    return answerReasonUnfilled;
  }

  public void setAnswerReasonUnfilled(String answerReasonUnfilled) {
    this.answerReasonUnfilled = answerReasonUnfilled;
  }

  public String getMaterialType() {
    return materialType;
  }

  public void setMaterialType(String materialType) {
    this.materialType = materialType;
  }

  public VdxServiceType getReqServiceType() {
    return reqServiceType;
  }

  public void setReqServiceType(VdxServiceType reqServiceType) {
    this.reqServiceType = reqServiceType;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 47 * hash + Objects.hashCode(this.respCampus);
    hash = 47 * hash + Objects.hashCode(this.respName);
    hash = 47 * hash + Objects.hashCode(this.reqLocType);
    hash = 47 * hash + Objects.hashCode(this.reqName);
    hash = 47 * hash + Objects.hashCode(this.illno);
    hash = 47 * hash + Objects.hashCode(this.reqTitle);
    hash = 47 * hash + Objects.hashCode(this.reqPubDate);
    hash = 47 * hash + Objects.hashCode(this.reqPublisher);
    hash = 47 * hash + Objects.hashCode(this.reqShelf);
    hash = 47 * hash + Objects.hashCode(this.answerReasonUnfilled);
    hash = 47 * hash + Objects.hashCode(this.materialType);
    hash = 47 * hash + Objects.hashCode(this.reqServiceType);
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
    final SpVdxLendingUnfilledDetail other = (SpVdxLendingUnfilledDetail) obj;
    if (!Objects.equals(this.respName, other.respName)) {
      return false;
    }
    if (!Objects.equals(this.reqLocType, other.reqLocType)) {
      return false;
    }
    if (!Objects.equals(this.reqName, other.reqName)) {
      return false;
    }
    if (!Objects.equals(this.reqTitle, other.reqTitle)) {
      return false;
    }
    if (!Objects.equals(this.reqPublisher, other.reqPublisher)) {
      return false;
    }
    if (!Objects.equals(this.reqShelf, other.reqShelf)) {
      return false;
    }
    if (!Objects.equals(this.answerReasonUnfilled, other.answerReasonUnfilled)) {
      return false;
    }
    if (!Objects.equals(this.materialType, other.materialType)) {
      return false;
    }
    if (this.respCampus != other.respCampus) {
      return false;
    }
    if (!Objects.equals(this.illno, other.illno)) {
      return false;
    }
    if (!Objects.equals(this.reqPubDate, other.reqPubDate)) {
      return false;
    }
    if (this.reqServiceType != other.reqServiceType) {
      return false;
    }
    return true;
  }

}
