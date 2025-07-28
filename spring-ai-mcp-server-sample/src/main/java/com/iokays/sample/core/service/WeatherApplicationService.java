package com.iokays.sample.core.service;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WeatherApplicationService {

    public String getWeather(String cityName) {
        if (Objects.equals(cityName, "深圳")) {
            return "晴天☀️";
        }
        return null;
    }
}
