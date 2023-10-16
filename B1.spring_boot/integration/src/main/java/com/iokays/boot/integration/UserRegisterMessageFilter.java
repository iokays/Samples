package com.iokays.boot.integration;

import org.springframework.integration.core.GenericSelector;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Spring Integration过滤器
 *
 */
@Component
public class UserRegisterMessageFilter implements GenericSelector<User> {
    @Override
    public boolean accept(User user) {
        return Objects.nonNull(user) && Objects.nonNull(user.name());
    }
}
