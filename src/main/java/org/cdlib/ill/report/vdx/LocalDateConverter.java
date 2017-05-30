package org.cdlib.ill.report.vdx;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * TODO: Spring Data JPA provides a converter that must be configured.
 * 
 * @author mmorrisp
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate x) {
        if (x == null) return null;
        return Date.valueOf(x);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date y) {
        if (y == null) return null;
        return y.toLocalDate();
    }
    
}
