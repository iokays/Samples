package com.iokays.common.core.command;

import java.io.Serializable;
import java.time.Instant;

/**
 * 命令接口
 */
public interface Command extends Serializable {

    /**
     * 从系统中获取当前时间
     *
     * @return {@link Instant} 当前时间
     */
    static Instant now() {
        return Instant.now();
    }

    /**
     * 获取命令的唯一标识
     *
     * @return {@link CommandId} 命令的唯一标识
     */
    CommandId id();
}
