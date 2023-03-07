package com.iokays.web.domain;

import com.google.common.collect.Maps;

import java.util.Map;

public interface BaseEnum {

    ThreadLocal<Map<Enum, String>> mapLocal = new ThreadLocal<>();

    default Map<String, Object> toMap() {

        final Map<Enum, String> value = getCacheMap();

        final Map<String, Object> result = Maps.newHashMap();
        result.put("val", ((Enum)this).name());
        result.put("text", value.get(this));

        return result;
    }

    private Map<Enum, String> getCacheMap() {
        var value = mapLocal.get();
        if (value == null) {
            value = Maps.newHashMap();
            mapLocal.set(value);
        }

        //实际根据klass根据缓存，读取数据。
        {
            final var klass = this.getClass().getName();
            value.put(UserTypeEnum.DISABLE, "已禁用");
            value.put(UserTypeEnum.ENABLE, "已启用");
        }
        return value;
    }
}
