package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class VdxILLCategorySerializer extends StdSerializer<VdxILLCategory> {

    public VdxILLCategorySerializer() {
        this(null);
    }

    public VdxILLCategorySerializer(Class<VdxILLCategory> t) {
        super(t);
    }

    @Override
    public void serialize(VdxILLCategory category, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeString(category.getDescr());
    }

}
