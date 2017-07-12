package org.cdlib.ill.report.vdx;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * TODO: Handle unexpected value in database with exception.
 * 
 * @author mmorrisp
 */
@Converter(autoApply = true)
public class VdxILLCategoryConverter implements AttributeConverter<VdxILLCategory, String> {

    @Override
    public String convertToDatabaseColumn(VdxILLCategory x) {
        if (x == null) return "";
        return x.getCode();
    }

    @Override
    public VdxILLCategory convertToEntityAttribute(String y) {
        return VdxILLCategory.fromCode(y).orElse(VdxILLCategory.OCLC);
    }
    
}
