package com.iokays.boot.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;

/**
 * Spring Integration DSL配置
 *
 */
@Configuration
public class UserRegisterIntegrationConfig {

    @Bean
    public IntegrationFlow registerFlow(UserRegisterSendHandler userRegisterSendHandler, UserRegisterMessageFilter userRegisterMessageFilter) {
        return IntegrationFlow
                // 从registerChannel消息通道获取消息
                .from(MessageChannels.direct("userRegisterChannel"))
                .filter(userRegisterMessageFilter)
                .handle(userRegisterSendHandler)
                .get();

    }
}
