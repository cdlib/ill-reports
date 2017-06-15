package org.cdlib.ill.report.vdx;

public abstract class VdxBaseRepository {

    protected static final String NULL_DATA_MSG = "Unexpected null database value.";
    protected static final RuntimeException BAD_DATA_EX = new IllegalArgumentException("Unexpected data.");
}
