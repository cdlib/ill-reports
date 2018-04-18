package org.cdlib.ill.report.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDate;

public class PreferredLocalDateFormatSerializer
    extends StdSerializer<LocalDate> {

  public PreferredLocalDateFormatSerializer() {
    this(null);
  }

  public PreferredLocalDateFormatSerializer(Class<LocalDate> t) {
    super(t);
  }

  @Override
  public void serialize(LocalDate t, JsonGenerator jg, SerializerProvider sp) throws IOException {
    jg.writeString(PreferredLocalDateFormat.FORMATTER.format(t));
  }

}
