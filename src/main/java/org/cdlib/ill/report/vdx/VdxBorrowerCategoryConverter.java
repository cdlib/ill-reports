package org.cdlib.ill.report.vdx;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.cdlib.ill.report.Constants;

@Converter(autoApply = true)
public class VdxBorrowerCategoryConverter implements AttributeConverter<VdxBorrowerCategory, String> {

  @Override
  public String convertToDatabaseColumn(VdxBorrowerCategory x) {
    if (x == null) {
      return null;
    }
    return x.getCode();
  }

  @Override
  public VdxBorrowerCategory convertToEntityAttribute(String y) {
    return VdxBorrowerCategory.fromCode(y).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER);
  }

}
