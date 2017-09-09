package org.cdlib.ill.report.vdx;

import java.util.Optional;

public enum VdxILLCategory {

    UC("U", "UC"),
    ISOPartners("I", "ISO"),
    OCLC("", "OCLC");

    private final String code;
    private final String descr;

    private VdxILLCategory(String code, String descr) {
        this.code = code;
        this.descr = descr;
    }

    public String getCode() {
        return code;
    }

    public String getDescr() {
        return descr;
    }

    public static Optional<VdxILLCategory> fromCode(String code) {
        for (VdxILLCategory category : values()) {
            if (category.getCode().equalsIgnoreCase(code)) {
                return Optional.of(category);
            }
        }
        return Optional.empty();
    }
}
