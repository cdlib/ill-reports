package org.cdlib.ill.report.vdx;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * * TODO: Handle unexpected value in database with exception.
 * 
 * @author mmorrisp
 */
@Converter(autoApply = true)
public class VdxBorrowerCategoryConverter implements AttributeConverter<VdxBorrowerCategory, String> {

    @Override
    public String convertToDatabaseColumn(VdxBorrowerCategory x) {
        if (x == null) {
            return "";
        }
        return x.getCode();
    }

    @Override
    public VdxBorrowerCategory convertToEntityAttribute(String y) {
        return VdxBorrowerCategory.fromCode(y).orElse(VdxBorrowerCategory.Unknown);
    }

}
