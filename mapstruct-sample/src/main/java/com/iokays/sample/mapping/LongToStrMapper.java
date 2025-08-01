package com.iokays.sample.mapping;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

import java.util.Objects;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * 这是一个通用的将Long转为Str的转换类
 */
@Mapper(
        componentModel = SPRING // Spring 环境可以注入到Spring容器中
)
public interface LongToStrMapper {

    default String convert(final Long id) {
        return Objects.toString(id, StringUtils.EMPTY);
    }
}
