package com.iokays.boot.mysql.integration.message;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

/**
 * Spring Integration网关入口
 *
 */
@Component
@MessagingGateway(defaultRequestChannel = "userRegisterChannel")
public interface UserRegisterMessageGateway {
    /**
     * 注册通知流程
     *
     * @param user 用户信息
     */
    void registerMessageFlow(User user);
}

