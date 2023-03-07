package com.iokays.web.seriallizer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iokays.web.domain.BaseEnum;

import java.io.IOException;

public class CustomEnumSerializer extends StdSerializer<BaseEnum> {

    public CustomEnumSerializer() {
        super(BaseEnum.class);
    }

    @Override
    public void serialize(BaseEnum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeObject(value.toMap());
    }
}
