package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class VdxShipDeliveryMethodSerializer extends StdSerializer<VdxShipDeliveryMethod> {

    public VdxShipDeliveryMethodSerializer() {
        this(null);
    }

    public VdxShipDeliveryMethodSerializer(Class<VdxShipDeliveryMethod> t) {
        super(t);
    }

    @Override
    public void serialize(VdxShipDeliveryMethod method, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeString(method.getCode());
    }

}
