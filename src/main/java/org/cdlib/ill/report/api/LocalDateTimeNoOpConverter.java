package org.cdlib.ill.report.api;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.springframework.lang.Nullable;

@Converter
public class LocalDateTimeNoOpConverter implements AttributeConverter<LocalDateTime, LocalDateTime> {

  @Nullable
  @Override
  public LocalDateTime convertToDatabaseColumn(LocalDateTime date) {
    return date == null ? null : date;
  }

  @Nullable
  @Override
  public LocalDateTime convertToEntityAttribute(LocalDateTime date) {
    return date == null ? null : date;
  }
}
