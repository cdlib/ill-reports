package org.cdlib.ill.report.vdx;

import java.util.Optional;

public enum VdxServiceType {

    Other("Other", "Other"),
    Loan("Loan", "Loan"),
    CopyNonReturnable("Copy non returnable", "Copy non-returnable");

    private final String code;
    private final String description;

    private VdxServiceType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<VdxServiceType> fromCode(String code) {
        for (VdxServiceType type : values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
