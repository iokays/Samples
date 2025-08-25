package com.iokays.common.mongodb;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class AbstractCodeConverter implements Converter<AbstractCode, String> {

    @Override
    public String convert(AbstractCode source) {
        return source.id();
    }
}