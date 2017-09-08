package org.cdlib.ill.report.vdx.procedures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
import java.util.Objects;
import org.cdlib.ill.report.api.PreferredLocalDateTimeFormatSerializer;
import org.cdlib.ill.report.vdx.VdxBorrowerCategory;
import org.cdlib.ill.report.vdx.VdxBorrowerCategorySerializer;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.cdlib.ill.report.vdx.VdxServiceTypeSerializer;

@JsonPropertyOrder({
    "illno",
    "borcat",
    "entryDate",
    "reqEdition",
    "reqIsbn",
    "reqIssn",
    "reqName",
    "reqPartPubDate",
    "reqPubDate",
    "reqPublisher",
    "reqPubPlace",
    "reqShelf",
    "reqTitle",
    "respName",
    "reqServiceType",
    "status"
})
public class SpVdxBorrowingDetail {

    @JsonProperty("req_name")
    private String reqName;
    @JsonProperty("illno")
    private Long illno;
    @JsonProperty("entry_date")
    @JsonSerialize(using = PreferredLocalDateTimeFormatSerializer.class)
    private LocalDateTime entryDate;
    @JsonProperty("borcat")
    @JsonSerialize(using = VdxBorrowerCategorySerializer.class)
    private VdxBorrowerCategory borcat;
    @JsonProperty("req_shelf")
    private String reqShelf;
    @JsonProperty("req_title")
    private String reqTitle;
    @JsonProperty("req_publisher")
    private String reqPublisher;
    @JsonProperty("req_pubplace")
    private String reqPubPlace;
    @JsonProperty("req_pubdate")
    private String reqPubDate;
    @JsonProperty("req_part_pubdate")
    private String reqPartPubDate;
    @JsonProperty("req_edition")
    private String reqEdition;
    @JsonProperty("req_isbn")
    private String reqIsbn;
    @JsonProperty("req_issn")
    private String reqIssn;
    @JsonProperty("resp_name")
    private String respName;
    @JsonProperty("status")
    private String status;
    @JsonProperty("servicetp")
    @JsonSerialize(using = VdxServiceTypeSerializer.class)
    private VdxServiceType reqServiceType;

    public SpVdxBorrowingDetail() {
    }

    public SpVdxBorrowingDetail(String reqName, Long illno, LocalDateTime entryDate, VdxBorrowerCategory borcat, String reqShelf, String reqTitle, String reqPublisher, String reqPubPlace, String reqPubDate, String reqPartPubDate, String reqEdition, String reqIsbn, String reqIssn, String respName, String status, VdxServiceType reqServiceType) {
        this.reqName = reqName;
        this.illno = illno;
        this.entryDate = entryDate;
        this.borcat = borcat;
        this.reqShelf = reqShelf;
        this.reqTitle = reqTitle;
        this.reqPublisher = reqPublisher;
        this.reqPubPlace = reqPubPlace;
        this.reqPubDate = reqPubDate;
        this.reqPartPubDate = reqPartPubDate;
        this.reqEdition = reqEdition;
        this.reqIsbn = reqIsbn;
        this.reqIssn = reqIssn;
        this.respName = respName;
        this.status = status;
        this.reqServiceType = reqServiceType;
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

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public VdxBorrowerCategory getBorcat() {
        return borcat;
    }

    public void setBorcat(VdxBorrowerCategory borcat) {
        this.borcat = borcat;
    }

    public String getReqShelf() {
        return reqShelf;
    }

    public void setReqShelf(String reqShelf) {
        this.reqShelf = reqShelf;
    }

    public String getReqTitle() {
        return reqTitle;
    }

    public void setReqTitle(String reqTitle) {
        this.reqTitle = reqTitle;
    }

    public String getReqPublisher() {
        return reqPublisher;
    }

    public void setReqPublisher(String reqPublisher) {
        this.reqPublisher = reqPublisher;
    }

    public String getReqPubPlace() {
        return reqPubPlace;
    }

    public void setReqPubPlace(String reqPubPlace) {
        this.reqPubPlace = reqPubPlace;
    }

    public String getReqPubDate() {
        return reqPubDate;
    }

    public void setReqPubDate(String reqPubDate) {
        this.reqPubDate = reqPubDate;
    }

    public String getReqPartPubDate() {
        return reqPartPubDate;
    }

    public void setReqPartPubDate(String reqPartPubDate) {
        this.reqPartPubDate = reqPartPubDate;
    }

    public String getReqEdition() {
        return reqEdition;
    }

    public void setReqEdition(String reqEdition) {
        this.reqEdition = reqEdition;
    }

    public String getReqIsbn() {
        return reqIsbn;
    }

    public void setReqIsbn(String reqIsbn) {
        this.reqIsbn = reqIsbn;
    }

    public String getReqIssn() {
        return reqIssn;
    }

    public void setReqIssn(String reqIssn) {
        this.reqIssn = reqIssn;
    }

    public String getRespName() {
        return respName;
    }

    public void setRespName(String respName) {
        this.respName = respName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VdxServiceType getReqServiceType() {
        return reqServiceType;
    }

    public void setReqServiceType(VdxServiceType reqServiceType) {
        this.reqServiceType = reqServiceType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.reqName);
        hash = 23 * hash + Objects.hashCode(this.illno);
        hash = 23 * hash + Objects.hashCode(this.entryDate);
        hash = 23 * hash + Objects.hashCode(this.borcat);
        hash = 23 * hash + Objects.hashCode(this.reqShelf);
        hash = 23 * hash + Objects.hashCode(this.reqTitle);
        hash = 23 * hash + Objects.hashCode(this.reqPublisher);
        hash = 23 * hash + Objects.hashCode(this.reqPubPlace);
        hash = 23 * hash + Objects.hashCode(this.reqPubDate);
        hash = 23 * hash + Objects.hashCode(this.reqPartPubDate);
        hash = 23 * hash + Objects.hashCode(this.reqEdition);
        hash = 23 * hash + Objects.hashCode(this.reqIsbn);
        hash = 23 * hash + Objects.hashCode(this.reqIssn);
        hash = 23 * hash + Objects.hashCode(this.respName);
        hash = 23 * hash + Objects.hashCode(this.status);
        hash = 23 * hash + Objects.hashCode(this.reqServiceType);
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
        final SpVdxBorrowingDetail other = (SpVdxBorrowingDetail) obj;
        if (!Objects.equals(this.reqName, other.reqName)) {
            return false;
        }
        if (!Objects.equals(this.reqShelf, other.reqShelf)) {
            return false;
        }
        if (!Objects.equals(this.reqTitle, other.reqTitle)) {
            return false;
        }
        if (!Objects.equals(this.reqPublisher, other.reqPublisher)) {
            return false;
        }
        if (!Objects.equals(this.reqPubPlace, other.reqPubPlace)) {
            return false;
        }
        if (!Objects.equals(this.reqPubDate, other.reqPubDate)) {
            return false;
        }
        if (!Objects.equals(this.reqPartPubDate, other.reqPartPubDate)) {
            return false;
        }
        if (!Objects.equals(this.reqEdition, other.reqEdition)) {
            return false;
        }
        if (!Objects.equals(this.reqIsbn, other.reqIsbn)) {
            return false;
        }
        if (!Objects.equals(this.reqIssn, other.reqIssn)) {
            return false;
        }
        if (!Objects.equals(this.respName, other.respName)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.illno, other.illno)) {
            return false;
        }
        if (!Objects.equals(this.entryDate, other.entryDate)) {
            return false;
        }
        if (this.borcat != other.borcat) {
            return false;
        }
        if (this.reqServiceType != other.reqServiceType) {
            return false;
        }
        return true;
    }

}
