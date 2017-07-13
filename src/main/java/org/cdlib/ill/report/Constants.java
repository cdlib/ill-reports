package org.cdlib.ill.report;

import java.util.function.Supplier;

public final class Constants {

    public static final String NULL_DATA_MSG = "Unexpected null database value.";
    public static final String BAD_PROCEDURE_MSG = "Unexpected output of stored procedure.";
    public static final RuntimeException BAD_DATA_EX = new IllegalArgumentException("Unexpected data.");
    public static final Supplier<RuntimeException> BAD_DATA_EX_SUPPLIER = () -> {
        return BAD_DATA_EX;
    };
}
