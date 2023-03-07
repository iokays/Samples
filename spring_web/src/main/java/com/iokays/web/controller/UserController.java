package com.iokays.web.controller;

import com.iokays.web.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jeasy.random.EasyRandom;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class UserController {

    private static final EasyRandom er = new EasyRandom();

    @GetMapping(value = "/users")
    public List<User> users(@RequestParam(value = "value", required = false) String value,
                            HttpServletRequest request, HttpServletResponse response) {
        return IntStream.range(0, 5)
                .mapToObj(v -> er.nextObject(User.class))
                .collect(Collectors.toList());
    }

    @PostMapping(value="/user")
    public User save(@RequestBody User user) {
        return user;
    }

}
