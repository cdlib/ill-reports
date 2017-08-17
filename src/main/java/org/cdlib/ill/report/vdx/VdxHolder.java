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

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @JsonProperty("symbol")
    private String symbol;

    @Column(nullable = false)
    @JsonProperty("campus")
    @JsonSerialize(using = VdxCampusSerializer.class)
    private VdxCampus campus;

    @Column(nullable = false)
    @JsonProperty("category")
    @JsonSerialize(using = VdxILLCategorySerializer.class)
    private VdxILLCategory category;

    @Column(nullable = false)
    @JsonProperty("loctype")
    private String loctype;

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

    public String getLoctype() {
        return loctype;
    }

    public void setLoctype(String loctype) {
        this.loctype = loctype;
    }

}
