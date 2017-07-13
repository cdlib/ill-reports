package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class VdxCampusSerializer extends StdSerializer<VdxCampus> {

    public VdxCampusSerializer() {
        this(null);
    }

    public VdxCampusSerializer(Class<VdxCampus> t) {
        super(t);
    }

    @Override
    public void serialize(VdxCampus campus, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeString(campus.getCode());
    }

}
