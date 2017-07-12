package org.cdlib.ill.report.vdx;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * A lender or borrower of records, as a subset of columns on
 * {@link VdxBorrowing} table.
 *
 * @author mmorrisp
 */
@Embeddable
public class VdxHolder implements Serializable {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private VdxCampus campus;

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
