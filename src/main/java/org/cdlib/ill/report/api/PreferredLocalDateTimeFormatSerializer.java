package org.cdlib.ill.report.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDateTime;

public class PreferredLocalDateTimeFormatSerializer
        extends StdSerializer<LocalDateTime> {

    public PreferredLocalDateTimeFormatSerializer() {
        this(null);
    }

    public PreferredLocalDateTimeFormatSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDateTime t, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeString(PreferredLocalDateTimeFormat.FORMATTER.format(t));
    }

}
