package com.iokays.integration.localmessage.event;

import java.time.LocalDateTime;

/**
 * 事件存储对象
 * @param eventId 事件ID
 * @param eventBody 事件内容
 * @param occurredOn 事件发生时间
 * @param typeName 事件类型
 * @param createTime 事件创建时间
 */
public record StoredEvent(long eventId, String eventBody, LocalDateTime occurredOn, String typeName, LocalDateTime createTime) { }
