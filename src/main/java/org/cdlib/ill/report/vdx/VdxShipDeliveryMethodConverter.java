package org.cdlib.ill.report.vdx;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.cdlib.ill.report.Constants;

@Converter(autoApply = true)
public class VdxShipDeliveryMethodConverter implements AttributeConverter<VdxShipDeliveryMethod, String> {

  @Override
  public String convertToDatabaseColumn(VdxShipDeliveryMethod x) {
    if (x == null) {
      return null;
    }
    return x.getCode();
  }

  @Override
  public VdxShipDeliveryMethod convertToEntityAttribute(String y) {
    return VdxShipDeliveryMethod.fromCode(y).orElseThrow(Constants.BAD_DATA_EX_SUPPLIER);
  }

}
