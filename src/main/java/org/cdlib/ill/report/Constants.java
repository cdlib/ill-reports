package org.cdlib.ill.report;

public final class Constants {

    public static final String NULL_DATA_MSG = "Unexpected null database value.";
    public static final String BAD_PROCEDURE_MSG = "Unexpected output of stored procedure.";
    public static final RuntimeException BAD_DATA_EX = new IllegalArgumentException("Unexpected data.");
}
