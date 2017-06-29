package org.cdlib.ill.report.vdx;

import java.util.Optional;

public enum VdxShipDeliveryMethod {

    Other("", "Other"),
    Courier("Courier", "Courier"),
    Email("Email", "Email"),
    FTP("FTP", "FTP"),
    Postal("Postal", "Postal");

    private final String code;
    private final String description;

    private VdxShipDeliveryMethod(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<VdxShipDeliveryMethod> fromCode(String code) {
        for (VdxShipDeliveryMethod type : values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
