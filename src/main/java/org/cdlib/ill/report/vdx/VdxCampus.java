package org.cdlib.ill.report.vdx;

import java.util.Optional;

/**
 * Mapping of campus identifiers in {@link VdxBorrowing} table.
 * 
 * @author mmorrisp
 */
public enum VdxCampus {

    None(""), // TODO: Talk with Joe/Debra about this assumption.
    UCLADocumentDelivery("OELA"),
    NorthernRegionalLibraryFacility("NRLF"),
    SouthernRegionalLibraryFacility("SRLF"),
    Berkeley("UCB"),
    Davis("UCD"),
    Irvine("UCI"),
    LosAngeles("UCLA"),
    Merced("UCM"),
    Riverside("UCR"),
    SantaBarabara("UCSB"),
    SantaCruz("UCSC"),
    SanDiego("UCSD"),
    SanFrancisco("UCSF");

    private final String code;

    private VdxCampus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
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
