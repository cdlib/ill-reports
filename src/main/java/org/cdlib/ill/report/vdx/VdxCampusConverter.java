package org.cdlib.ill.report.vdx;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * TODO: Handle unexpected value in database with exception.
 *
 * @author mmorrisp
 */
@Converter(autoApply = true)
public class VdxCampusConverter implements AttributeConverter<VdxCampus, String> {

    @Override
    public String convertToDatabaseColumn(VdxCampus x) {
        if (x == null) {
            return "";
        }
        return x.getCode();
    }

    @Override
    public VdxCampus convertToEntityAttribute(String y) {
        return VdxCampus.fromCode(y).orElse(VdxCampus.None);
    }

}
