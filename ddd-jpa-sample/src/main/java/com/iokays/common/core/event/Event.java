package com.iokays.common.core.event;

import com.iokays.common.core.message.Message;

/**
 * 事件接口
 */
public interface Event extends Message {

    EventId eventId();

}
