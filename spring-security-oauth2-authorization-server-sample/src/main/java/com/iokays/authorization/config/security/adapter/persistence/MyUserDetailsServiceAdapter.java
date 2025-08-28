package com.iokays.authorization.config.security.adapter.persistence;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

/**
 * 用户体系的适配器
 */
@Component
public class MyUserDetailsServiceAdapter implements UserDetailsService {

    //修改注入实际为业务层存储服务
    private final InMemoryUserDetailsManager manager;

    public MyUserDetailsServiceAdapter() {
        final UserDetails user = User.builder().username("user").password("{noop}123456").roles("ADMIN").build();
        manager = new InMemoryUserDetailsManager(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return manager.loadUserByUsername(username);
    }
}
