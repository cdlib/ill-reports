package org.cdlib.ill.report.vdx;

/**
 * Mapping of output for stored procedure {@code sp_vdx_lending_summary}.
 *
 * @author mmorrisp
 */
public class VdxLendingSummary {

    private VdxCampus respCampus;
    private String respName;
    private VdxCategory reqCategory;
    private Long count;

    public VdxLendingSummary() {
    }

    public VdxLendingSummary(VdxCampus reqCampus, String reqName, VdxCategory respCategory, Long count) {
        this.respCampus = reqCampus;
        this.respName = reqName;
        this.reqCategory = respCategory;
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

}
