package org.cdlib.ill.report.vdx;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.cdlib.ill.report.Constants;

@Converter(autoApply = true)
public class VdxCampusConverter implements AttributeConverter<VdxCampus, String> {

  @Override
  public String convertToDatabaseColumn(VdxCampus x) {
    if (x == null) {
      return null;
    }
    return x.getCode();
  }

  @Override
  public VdxCampus convertToEntityAttribute(String y) {
    return VdxCampus.fromCode(y).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER);
  }

}
