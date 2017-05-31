package org.cdlib.ill.report.vdx;

/**
 * Mapping of output for stored procedure {@code sp_vdx_borrowing_summary}.
 *
 * @author mmorrisp
 */
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

}
