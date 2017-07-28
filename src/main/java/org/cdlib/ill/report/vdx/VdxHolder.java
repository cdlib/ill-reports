package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("campus")
    @Column(nullable = false)
    private VdxCampus campus;

    @JsonProperty("category")
    @Column(nullable = false)
    private VdxILLCategory category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
