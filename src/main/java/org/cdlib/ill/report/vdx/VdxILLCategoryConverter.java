package org.cdlib.ill.report.vdx;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.cdlib.ill.report.Constants;

@Converter(autoApply = true)
public class VdxILLCategoryConverter implements AttributeConverter<VdxILLCategory, String> {

  @Override
  public String convertToDatabaseColumn(VdxILLCategory x) {
    if (x == null) {
      return null;
    }
    return x.getCode();
  }

  @Override
  public VdxILLCategory convertToEntityAttribute(String y) {
    return VdxILLCategory.fromCode(y).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER);
  }

}
