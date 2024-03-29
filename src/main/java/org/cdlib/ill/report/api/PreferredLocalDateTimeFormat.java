package org.cdlib.ill.report.api;

import java.time.format.DateTimeFormatter;

public class PreferredLocalDateTimeFormat {

  public static final String PATTERN = "MM/dd/yyyy HH:mm:ss a";
  public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
}
