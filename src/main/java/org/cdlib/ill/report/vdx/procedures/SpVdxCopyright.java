package org.cdlib.ill.report.vdx.procedures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxCampusSerializer;

@JsonPropertyOrder({
    "reqCampus",
    "reqTitle",
    "pubYear",
    "count"
})
public class SpVdxCopyright {

    @JsonProperty("borrowing campus")
    @JsonSerialize(using = VdxCampusSerializer.class)
    private VdxCampus reqCampus;
    @JsonProperty("requested title")
    private String reqTitle;
    @JsonProperty("publication year")
    private String pubYear;
    @JsonProperty("total")
    private Long count;

    public SpVdxCopyright() {
    }

    public SpVdxCopyright(VdxCampus reqCampus, String reqTitle, String pubYear, Long count) {
        this.reqCampus = reqCampus;
        this.reqTitle = reqTitle;
        this.pubYear = pubYear;
        this.count = count;
    }

    public VdxCampus getReqCampus() {
        return reqCampus;
    }

    public void setReqCampus(VdxCampus reqCampus) {
        this.reqCampus = reqCampus;
    }

    public String getReqTitle() {
        return reqTitle;
    }

    public void setReqTitle(String reqTitle) {
        this.reqTitle = reqTitle;
    }

    public String getPubYear() {
        return pubYear;
    }

    public void setPubYear(String pubYear) {
        this.pubYear = pubYear;
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
        hash = 11 * hash + Objects.hashCode(this.reqCampus);
        hash = 11 * hash + Objects.hashCode(this.reqTitle);
        hash = 11 * hash + Objects.hashCode(this.pubYear);
        hash = 11 * hash + Objects.hashCode(this.count);
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
        final SpVdxCopyright other = (SpVdxCopyright) obj;
        if (!Objects.equals(this.reqTitle, other.reqTitle)) {
            return false;
        }
        if (this.reqCampus != other.reqCampus) {
            return false;
        }
        if (!Objects.equals(this.pubYear, other.pubYear)) {
            return false;
        }
        if (!Objects.equals(this.count, other.count)) {
            return false;
        }
        return true;
    }

}
