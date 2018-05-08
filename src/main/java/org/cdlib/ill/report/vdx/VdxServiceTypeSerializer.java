package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class VdxServiceTypeSerializer extends StdSerializer<VdxServiceType> {

  public VdxServiceTypeSerializer() {
    this(null);
  }

  public VdxServiceTypeSerializer(Class<VdxServiceType> t) {
    super(t);
  }

  @Override
  public void serialize(VdxServiceType serviceType, JsonGenerator jg, SerializerProvider sp) throws IOException {
    jg.writeString(serviceType.getCode());
  }
}
