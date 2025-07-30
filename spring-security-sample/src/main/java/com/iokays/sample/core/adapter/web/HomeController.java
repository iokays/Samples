package com.iokays.sample.core.adapter.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String hellworld() {
        return "Hello World";
    }
}
