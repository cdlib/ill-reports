package org.cdlib.ill.model;

/**
 * Any lending or borrowing institution, and the libraries that compose it.
 * 
 * @author mmorrisp
 */
public class Institution {

    private String name;
    private String campus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

}
