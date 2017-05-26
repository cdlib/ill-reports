package org.cdlib.ill.report.vdx;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author mmorrisp
 */
@Converter(autoApply = true)
public class VdxCampusConverter implements AttributeConverter<VdxCampus, String> {

    @Override
    public String convertToDatabaseColumn(VdxCampus x) {
        if (x == null) return "";
        return x.getCode();
    }

    @Override
    public VdxCampus convertToEntityAttribute(String y) {
        return VdxCampus.fromCode(y).orElse(VdxCampus.None);
    }
    
}
