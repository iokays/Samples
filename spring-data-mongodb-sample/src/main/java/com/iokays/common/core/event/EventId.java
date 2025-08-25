package com.iokays.common.core.event;

import java.io.Serializable;
import java.util.UUID;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * 事件ID
 *
 * @param id {@link UUID} 事件唯一标识ID
 */
public record EventId(String id) implements Serializable {

    public EventId {
        notNull(id, "事件ID不能为空");
    }

    /**
     * 创建事件ID {@link EventId}
     *
     * @param value {@link String} UUID类型的原始字符串
     * @return 事件ID {@link EventId}
     */
    public static EventId form(final String value) {
        return new EventId(UUID.fromString(value).toString());
    }

    /**
     * 创建一个唯一的事件ID {@link EventId}
     *
     * @return 事件ID {@link EventId}
     */
    public static EventId generate() {
        return new EventId(UUID.randomUUID().toString());
    }
}
