package org.cdlib.ill.report.api;

import java.time.format.DateTimeFormatter;

public final class PreferredLocalDateFormat {

    public static final String PATTERN = "MM/dd/YYYY";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
}
