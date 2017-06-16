package org.cdlib.ill.report.vdx;

import java.util.Objects;

public class VdxBorrowingSummary {

    private VdxCampus reqCampus;
    private String reqName;
    private VdxCategory respCategory;
    private Long count;

    public VdxBorrowingSummary() {
    }

    public VdxBorrowingSummary(VdxCampus reqCampus, String reqName, VdxCategory respCategory, Long count) {
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

    public VdxCategory getRespCategory() {
        return respCategory;
    }

    public void setRespCategory(VdxCategory respCategory) {
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
        final VdxBorrowingSummary other = (VdxBorrowingSummary) obj;
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
