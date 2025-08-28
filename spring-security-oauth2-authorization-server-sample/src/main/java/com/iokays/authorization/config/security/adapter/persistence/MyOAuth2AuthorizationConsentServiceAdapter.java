package com.iokays.authorization.config.security.adapter.persistence;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MyOAuth2AuthorizationConsentServiceAdapter implements OAuth2AuthorizationConsentService {

    //修改注入实际为业务层存储服务
    private final InMemoryOAuth2AuthorizationConsentService authorizationConsentApplicationService = new InMemoryOAuth2AuthorizationConsentService();

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        log.info("authorizationConsent: {}", authorizationConsent); //生产上, 禁止打印
        this.authorizationConsentApplicationService.save(authorizationConsent);
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        this.authorizationConsentApplicationService.remove(authorizationConsent);
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        return authorizationConsentApplicationService.findById(registeredClientId, principalName);
    }

}
