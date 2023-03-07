package com.iokays.web.seriallizer;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.iokays.web.domain.BaseEnum;

public class CustomBeanSerializerModifier extends BeanSerializerModifier {

    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        if (BaseEnum.class.isAssignableFrom(beanDesc.getBeanClass())) {
            return new CustomEnumSerializer();
        }
        return super.modifySerializer(config, beanDesc, serializer);
    }
}
