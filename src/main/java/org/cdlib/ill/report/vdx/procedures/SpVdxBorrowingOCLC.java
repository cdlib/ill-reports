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
    "reqCampus",
    "reqName",
    "respName",
    "serviceTp",
    "count"
})
public class SpVdxBorrowingOCLC {

    @JsonProperty("borrowing campus")
    @JsonSerialize(using = VdxCampusSerializer.class)
    private VdxCampus reqCampus;
    @JsonProperty("borrowing library")
    private String reqName;
    @JsonProperty("lending library")
    private String respName;
    @JsonProperty("loan service")
    @JsonSerialize(using = VdxServiceTypeSerializer.class)
    private VdxServiceType serviceTp;
    @JsonProperty("total")
    private Long count;

    public SpVdxBorrowingOCLC() {
    }

    public SpVdxBorrowingOCLC(VdxCampus reqCampus, String reqName, String respName, VdxServiceType serviceTp, Long count) {
        this.reqCampus = reqCampus;
        this.reqName = reqName;
        this.respName = respName;
        this.serviceTp = serviceTp;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.reqCampus);
        hash = 73 * hash + Objects.hashCode(this.reqName);
        hash = 73 * hash + Objects.hashCode(this.respName);
        hash = 73 * hash + Objects.hashCode(this.serviceTp);
        hash = 73 * hash + Objects.hashCode(this.count);
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
        final SpVdxBorrowingOCLC other = (SpVdxBorrowingOCLC) obj;
        if (!Objects.equals(this.reqName, other.reqName)) {
            return false;
        }
        if (!Objects.equals(this.respName, other.respName)) {
            return false;
        }
        if (this.reqCampus != other.reqCampus) {
            return false;
        }
        if (this.serviceTp != other.serviceTp) {
            return false;
        }
        if (!Objects.equals(this.count, other.count)) {
            return false;
        }
        return true;
    }

}
