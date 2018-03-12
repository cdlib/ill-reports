package org.cdlib.ill.report.vdx;

import java.util.Optional;

public enum VdxCampus {

    None("", "None"),
    NorthernRegionalLibraryFacility("NRLF", "NRLF"),
    SouthernRegionalLibraryFacility("SRLF", "SRLF"),
    Berkeley("UCB", "Berkeley"),
    Davis("UCD", "Davis"),
    Irvine("UCI", "Irvine"),
    LosAngeles("UCLA", "Los Angeles"),
    UCLADocumentDelivery("OELA", "UCLA Document Delivery"),
    Merced("UCM", "Merced"),
    Riverside("UCR", "Riverside"),
    SantaBarbara("UCSB", "Santa Barbara"),
    SantaCruz("UCSC", "Santa Cruz"),
    SanDiego("UCSD", "San Diego"),
    SanFrancisco("UCSF", "San Francisco");

    private final String code;
    private final String description;

    private VdxCampus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<VdxCampus> fromCode(String code) {
        for (VdxCampus campus : values()) {
            if (campus.getCode().equalsIgnoreCase(code)) {
                return Optional.of(campus);
            }
        }
        return Optional.empty();
    }

}
