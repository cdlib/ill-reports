package org.cdlib.ill.report.vdx;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.cdlib.ill.report.Constants;

@Converter(autoApply = true)
public class VdxServiceTypeConverter implements AttributeConverter<VdxServiceType, String> {

  @Override
  public String convertToDatabaseColumn(VdxServiceType x) {
    if (x == null) {
      return null;
    }
    return x.getCode();
  }

  @Override
  public VdxServiceType convertToEntityAttribute(String y) {
    return VdxServiceType.fromCode(y).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER);
  }

}
