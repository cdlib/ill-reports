package org.cdlib.ill.report.vdx;

import java.util.Objects;

public class VdxLendingSummary {

    private VdxCampus respCampus;
    private String respName;
    private VdxCategory reqCategory;
    private Long count;

    public VdxLendingSummary() {
    }

    public VdxLendingSummary(VdxCampus respCampus, String respName, VdxCategory reqCategory, Long count) {
        this.respCampus = respCampus;
        this.respName = respName;
        this.reqCategory = reqCategory;
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

    public VdxCategory getReqCategory() {
        return reqCategory;
    }

    public void setReqCategory(VdxCategory reqCategory) {
        this.reqCategory = reqCategory;
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
        hash = 71 * hash + Objects.hashCode(this.respCampus);
        hash = 71 * hash + Objects.hashCode(this.respName);
        hash = 71 * hash + Objects.hashCode(this.reqCategory);
        hash = 71 * hash + Objects.hashCode(this.count);
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
        final VdxLendingSummary other = (VdxLendingSummary) obj;
        if (!Objects.equals(this.respName, other.respName)) {
            return false;
        }
        if (this.respCampus != other.respCampus) {
            return false;
        }
        if (this.reqCategory != other.reqCategory) {
            return false;
        }
        if (!Objects.equals(this.count, other.count)) {
            return false;
        }
        return true;
    }

}
