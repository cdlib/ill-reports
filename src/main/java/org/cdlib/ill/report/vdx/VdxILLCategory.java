package org.cdlib.ill.report.vdx;

import java.util.Optional;

public enum VdxILLCategory {

    UC("U"),
    ISOPartners("I"),
    OCLC("");

    private final String code;

    private VdxILLCategory(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
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
