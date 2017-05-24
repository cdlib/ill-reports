package org.cdlib.ill.report.vdx;

/**
 * Mapping of category types in {@link VdxBorrowing} table.
 * 
 * @author mmorrisp
 */
public enum VdxCategory {

    UC("U"),
    ISOPartners("I"),
    OCLC("");

    private final String code;

    private VdxCategory(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
