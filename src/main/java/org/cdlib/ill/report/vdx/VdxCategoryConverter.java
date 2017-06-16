package org.cdlib.ill.report.vdx;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class VdxCategoryConverter implements AttributeConverter<VdxCategory, String> {

    @Override
    public String convertToDatabaseColumn(VdxCategory x) {
        if (x == null) return "";
        return x.getCode();
    }

    @Override
    public VdxCategory convertToEntityAttribute(String y) {
        return VdxCategory.fromCode(y).orElse(VdxCategory.OCLC);
    }
    
}
