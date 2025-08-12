package com.iokays.core.domain.customer.command;

import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import com.iokays.core.domain.customer.EmailAddress;
import com.iokays.core.domain.customer.FullName;
import com.iokays.core.domain.customer.Gender;

import java.time.Instant;

/**
 * 注册新客户的不可变记录命令
 *
 * @param id           {@link CommandId} 命令ID
 * @param registeredAt {@link Instant} 登记时间
 * @param fullName     {@link FullName} 客户的姓名
 * @param gender       {@link Gender} 客户的性别
 * @param emailAddress {@link EmailAddress} 客户的邮箱地址
 */
public record RegisterCustomer(CommandId id,
                               Instant registeredAt,
                               FullName fullName,
                               Gender gender,
                               EmailAddress emailAddress) implements Command {

    /**
     * 发出一个注册新客户的命令
     *
     * @param fullName     {@link FullName} 客户的姓名
     * @param gender       {@link Gender} 客户的性别
     * @param emailAddress {@link EmailAddress} 客户的邮箱地址
     * @return 返回一个注册新客户的命令 {@link RegisterCustomer}
     */
    public static RegisterCustomer issue(final FullName fullName,
                                         final Gender gender,
                                         final EmailAddress emailAddress) {
        return new RegisterCustomer(
                CommandId.generate(),
                Instant.now(),
                fullName,
                gender,
                emailAddress
        );
    }
}