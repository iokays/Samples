package com.iokays.web.service;

import com.iokays.web.domain.User;
import com.iokays.web.domain.UserTypeEnum;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    public String create(final String userName, final UserTypeEnum userType) {
        final User user = new User(UUID.randomUUID().toString(), userName, userType);

        return user.getUserId();
    }

}
