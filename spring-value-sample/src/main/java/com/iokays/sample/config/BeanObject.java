package com.iokays.sample.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BeanObject {

    private final String name = "this is bean object";

}
