package org.cdlib.ill.report.vdx.procedures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.YearSerializer;
import java.time.Year;
import java.util.Objects;
import org.cdlib.ill.report.vdx.VdxBorrowerCategory;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxCampusSerializer;

@JsonPropertyOrder({
    "reqCampus",
    "reqName",
    "reqTitle",
    "pubYear",
    "reqIssueTitle",
    "pagination",
    "borcat",
    "count"
})
public class SpVdxJournalBorrowing {

    @JsonProperty("borrowing campus")
    @JsonSerialize(using = VdxCampusSerializer.class)
    private VdxCampus reqCampus;
    @JsonProperty("borrowing library")
    private String reqName;
    @JsonProperty("requested title")
    private String reqTitle;
    @JsonProperty("publication year")
    @JsonSerialize(using = YearSerializer.class)
    private Year pubYear;
    @JsonProperty("requested issue")
    private String reqIssueTitle;
    @JsonProperty("pagination")
    private String pagination;
    @JsonProperty("borrower category")
    private VdxBorrowerCategory borcat;
    @JsonProperty("total")
    private Long count;

    public SpVdxJournalBorrowing() {
    }

    public SpVdxJournalBorrowing(
            VdxCampus reqCampus,
            String reqName,
            String reqTitle,
            Year pubYear,
            String reqIssueTitle,
            String pagination,
            VdxBorrowerCategory borcat,
            Long count) {
        this.reqCampus = reqCampus;
        this.reqName = reqName;
        this.reqTitle = reqTitle;
        this.pubYear = pubYear;
        this.reqIssueTitle = reqIssueTitle;
        this.pagination = pagination;
        this.borcat = borcat;
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

    public String getReqTitle() {
        return reqTitle;
    }

    public void setReqTitle(String reqTitle) {
        this.reqTitle = reqTitle;
    }

    public Year getPubYear() {
        return pubYear;
    }

    public void setPubYear(Year pubYear) {
        this.pubYear = pubYear;
    }

    public String getReqIssueTitle() {
        return reqIssueTitle;
    }

    public void setReqIssueTitle(String reqIssueTitle) {
        this.reqIssueTitle = reqIssueTitle;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public VdxBorrowerCategory getBorcat() {
        return borcat;
    }

    public void setBorcat(VdxBorrowerCategory borcat) {
        this.borcat = borcat;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.reqCampus);
        hash = 31 * hash + Objects.hashCode(this.reqName);
        hash = 31 * hash + Objects.hashCode(this.reqTitle);
        hash = 31 * hash + Objects.hashCode(this.pubYear);
        hash = 31 * hash + Objects.hashCode(this.reqIssueTitle);
        hash = 31 * hash + Objects.hashCode(this.pagination);
        hash = 31 * hash + Objects.hashCode(this.borcat);
        hash = 31 * hash + Objects.hashCode(this.count);
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
        final SpVdxJournalBorrowing other = (SpVdxJournalBorrowing) obj;
        if (!Objects.equals(this.reqName, other.reqName)) {
            return false;
        }
        if (!Objects.equals(this.reqTitle, other.reqTitle)) {
            return false;
        }
        if (!Objects.equals(this.reqIssueTitle, other.reqIssueTitle)) {
            return false;
        }
        if (!Objects.equals(this.pagination, other.pagination)) {
            return false;
        }
        if (this.reqCampus != other.reqCampus) {
            return false;
        }
        if (!Objects.equals(this.pubYear, other.pubYear)) {
            return false;
        }
        if (this.borcat != other.borcat) {
            return false;
        }
        if (!Objects.equals(this.count, other.count)) {
            return false;
        }
        return true;
    }

}
