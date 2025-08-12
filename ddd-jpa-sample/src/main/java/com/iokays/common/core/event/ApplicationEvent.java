package com.iokays.common.core.event;

/**
 * 应用事件接口
 * 该事件，一般用于业务的开始，同时该事件丢失，是可以接受的。
 */
public interface ApplicationEvent<T> extends Event {
}
