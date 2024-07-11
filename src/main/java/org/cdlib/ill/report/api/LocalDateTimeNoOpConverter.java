package org.cdlib.ill.report.api;

import java.time.LocalDateTime;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.lang.Nullable;

/*
 * This converter is used to prevent the framework from running its default converter on
 * DateTime instances provided by the MySQL 8 connector. The value expected by the ORM framework is java.Util.Date,
 * which causes a ClassCastException.
 * 
 * We couldn't find any way to simply turn off the default behavior, so this NoOp converter is applied
 * to all DateTime entity fields.
 */
@Converter
public class LocalDateTimeNoOpConverter implements AttributeConverter<LocalDateTime, LocalDateTime> {

  @Nullable
  @Override
  public LocalDateTime convertToDatabaseColumn(LocalDateTime date) {
    return date;
  }

  @Nullable
  @Override
  public LocalDateTime convertToEntityAttribute(LocalDateTime date) {
    return date;
  }
}
