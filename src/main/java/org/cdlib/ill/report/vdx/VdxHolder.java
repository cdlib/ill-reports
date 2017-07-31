package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * A lender or borrower of records, as a subset of columns on
 * {@link VdxBorrowing} or {@link VdxLending} table.
 *
 * @author mmorrisp
 */
@Embeddable
public class VdxHolder implements Serializable {

    @JsonProperty("name")
    @Column(nullable = false)
    private String name;

    @JsonProperty("symbol")
    @Column(nullable = false)
    private String symbol;

    @JsonProperty("campus")
    @Column(nullable = false)
    @JsonSerialize(using = VdxCampusSerializer.class)
    private VdxCampus campus;

    @JsonProperty("category")
    @Column(nullable = false)
    @JsonSerialize(using = VdxILLCategorySerializer.class)
    private VdxILLCategory category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public VdxCampus getCampus() {
        return campus;
    }

    public void setCampus(VdxCampus campus) {
        this.campus = campus;
    }

    public VdxILLCategory getCategory() {
        return category;
    }

    public void setCategory(VdxILLCategory category) {
        this.category = category;
    }

}
