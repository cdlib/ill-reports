package org.cdlib.ill.report.vdx;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class VdxBorrowerCategorySerializer extends StdSerializer<VdxBorrowerCategory> {

    public VdxBorrowerCategorySerializer() {
        this(null);
    }

    public VdxBorrowerCategorySerializer(Class<VdxBorrowerCategory> t) {
        super(t);
    }

    @Override
    public void serialize(VdxBorrowerCategory category, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeString(category.getCode());
    }

}
