package com.iokays.authorization.config.security.adapter.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyOAuth2AuthorizationServiceAdapter implements OAuth2AuthorizationService {

    //修改注入实际为业务层存储服务
    private final InMemoryOAuth2AuthorizationService authorizationApplicationService = new InMemoryOAuth2AuthorizationService();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void save(OAuth2Authorization authorization) {
        Validate.notNull(authorization, "authorization cannot be null");
        Try.run(() -> {
            log.info("authorization: {}", objectMapper.writeValueAsString(authorization)); //生产上, 禁止打印
        });
        authorizationApplicationService.save(authorization);
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        Validate.notNull(authorization, "authorization cannot be null");
        this.authorizationApplicationService.remove(authorization);
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return authorizationApplicationService.findById(id);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        return authorizationApplicationService.findByToken(token, tokenType);
    }

}

