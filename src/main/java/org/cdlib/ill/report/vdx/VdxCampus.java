package org.cdlib.ill.report.vdx;

import java.util.Optional;

/**
 * Mapping of campus identifiers in {@link VdxBorrowing} table.
 *
 * @author mmorrisp
 */
public enum VdxCampus {

    None("", "None"),
    UCLADocumentDelivery("OELA", "UCLA Document Delivery"),
    NorthernRegionalLibraryFacility("NRLF", "Northern Regional Library Facility"),
    SouthernRegionalLibraryFacility("SRLF", "Southern Regional Library Facility"),
    Berkeley("UCB", "UC Berkeley"),
    Davis("UCD", "UC Davis"),
    Irvine("UCI", "UC Irvine"),
    LosAngeles("UCLA", "UC Los Angeles"),
    Merced("UCM", "UC Merced"),
    Riverside("UCR", "UC Riverside"),
    SantaBarbara("UCSB", "UC Santa Barbara"),
    SantaCruz("UCSC", "UC Santa Cruz"),
    SanDiego("UCSD", "UC San Diego"),
    SanFrancisco("UCSF", "UC San Francisco");

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
