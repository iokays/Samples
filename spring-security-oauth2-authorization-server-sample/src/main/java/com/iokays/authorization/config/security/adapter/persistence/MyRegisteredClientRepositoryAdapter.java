package com.iokays.authorization.config.security.adapter.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

/**
 * Oauth2客户端体系的适配器
 * 为什么放一个适配器在这里, 是因为我们系统本身有一套业务数据体系, 而不是直接实现接口. 这样只需要对适配器进行修改即可
 * 在业务侧,就不用关系安全侧的细节.
 */
@Slf4j
@Component
public class MyRegisteredClientRepositoryAdapter implements RegisteredClientRepository {

    //修改注入实际为业务层存储服务
    private final InMemoryRegisteredClientRepository repository;

    public MyRegisteredClientRepositoryAdapter() {
        //模拟数据库数据
        final RegisteredClient registeredClient = RegisteredClient
                .withId(UUID.randomUUID().toString())
                .clientId("login-client")
                .clientSecret("{noop}secret")
                .clientName("授权码登录")
                .clientIdIssuedAt(Instant.now())
                .clientSecretExpiresAt(LocalDateTime.now().plusDays(1000).atZone(ZoneId.systemDefault()).toInstant())
                .clientAuthenticationMethods(v -> {
                    v.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
                    v.add(ClientAuthenticationMethod.NONE);
                })
                .authorizationGrantTypes(v -> {
                    v.add(AuthorizationGrantType.AUTHORIZATION_CODE);
                    v.add(AuthorizationGrantType.DEVICE_CODE);
                    v.add(AuthorizationGrantType.REFRESH_TOKEN);
                })
                .redirectUris(v -> v.add("http://localhost:8080/third/callback"))
                .scopes(v -> {
                    v.add(OidcScopes.OPENID);
                    v.add(OidcScopes.PROFILE);
                })
                .build();
        repository = new InMemoryRegisteredClientRepository(registeredClient);
    }


    @Override
    public void save(RegisteredClient registeredClient) {
        repository.save(registeredClient);
    }

    @Override
    public RegisteredClient findById(String id) {
        return repository.findById(id);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return repository.findByClientId(clientId);
    }
}
