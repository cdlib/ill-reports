package org.cdlib.ill.report.vdx.procedures;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;
import org.cdlib.ill.report.vdx.VdxBorrowerCategory;
import org.cdlib.ill.report.vdx.VdxBorrowerCategorySerializer;
import org.cdlib.ill.report.vdx.VdxCampus;
import org.cdlib.ill.report.vdx.VdxCampusSerializer;
import org.cdlib.ill.report.vdx.VdxServiceType;
import org.cdlib.ill.report.vdx.VdxServiceTypeSerializer;

@JsonPropertyOrder({
    "respCampus",
    "respName",
    "reqName",
    "serviceTp",
    "borcat",
    "count"
})
public class SpVdxLendingPatron {

    @JsonProperty("lending campus")
    @JsonSerialize(using = VdxCampusSerializer.class)
    private VdxCampus respCampus;
    @JsonProperty("lending library")
    private String respName;
    @JsonProperty("borrowing library")
    private String reqName;
    @JsonProperty("loan service")
    @JsonSerialize(using = VdxServiceTypeSerializer.class)
    private VdxServiceType serviceTp;
    @JsonProperty("patron category")
    @JsonSerialize(using = VdxBorrowerCategorySerializer.class)
    private VdxBorrowerCategory borcat;
    @JsonProperty("total")
    private Long count;

    public SpVdxLendingPatron() {
    }

    public SpVdxLendingPatron(VdxCampus respCampus, String respName, String reqName, VdxServiceType serviceTp, VdxBorrowerCategory borcat, Long count) {
        this.respCampus = respCampus;
        this.respName = respName;
        this.reqName = reqName;
        this.serviceTp = serviceTp;
        this.borcat = borcat;
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
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.respCampus);
        hash = 59 * hash + Objects.hashCode(this.respName);
        hash = 59 * hash + Objects.hashCode(this.reqName);
        hash = 59 * hash + Objects.hashCode(this.serviceTp);
        hash = 59 * hash + Objects.hashCode(this.borcat);
        hash = 59 * hash + Objects.hashCode(this.count);
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
        final SpVdxLendingPatron other = (SpVdxLendingPatron) obj;
        if (!Objects.equals(this.respName, other.respName)) {
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
        if (this.borcat != other.borcat) {
            return false;
        }
        if (!Objects.equals(this.count, other.count)) {
            return false;
        }
        return true;
    }

}
