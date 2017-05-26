package org.cdlib.ill.report.vdx;

import java.util.Optional;

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

    public static Optional<VdxCategory> fromCode(String code) {
        for (VdxCategory category : values()) {
            if (category.getCode().equalsIgnoreCase(code)) {
                return Optional.of(category);
            }
        }
        return Optional.empty();
    }
}
