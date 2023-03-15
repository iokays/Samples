package com.iokays.web.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iokays.web.seriallizer.CustomBeanSerializerModifier;
import org.jeasy.random.EasyRandom;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class User implements Serializable {

    private String userId;
    private String userName;
    private UserTypeEnum userType;

    public User() {
    }

    public User(String userId, String userName, UserTypeEnum userType) {
        this();
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public static void main(String[] args) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.setSerializerModifier(new CustomBeanSerializerModifier());
        objectMapper.registerModule(simpleModule);

        final EasyRandom er = new EasyRandom();

        final var list = IntStream.range(0, 5)
                .mapToObj(v -> er.nextObject(User.class))
                .collect(Collectors.toList());
        System.out.println(objectMapper.writeValueAsString(list));

    }

}
