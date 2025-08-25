package com.iokays.common.core.command;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * 不可变的记录类型，用于标识命令的唯一标识
 *
 * @param value {@link UUID} 命令的唯一标识
 */
public record CommandId(UUID value) implements Serializable {

    /**
     * 默认构造函数
     *
     * @param value {@link UUID} 命令的唯一标识
     */
    public CommandId {
        Objects.requireNonNull(value, "CommandId value must not be null");
    }

    /**
     * 从字符串中构造 {@link CommandId}
     *
     * @param value {@link String} UUID类型的原始字符串
     * @return 实例 {@link CommandId}
     */
    public static CommandId from(final String value) {
        return new CommandId(UUID.fromString(value));
    }

    /**
     * 生成一个唯一的 {@link CommandId}
     *
     * @return 实例 {@link CommandId}
     */
    public static CommandId generate() {
        return new CommandId(UUID.randomUUID());
    }

}
