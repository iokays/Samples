package com.iokays.sample.core.adapter.mcp;

import com.iokays.sample.core.service.WeatherApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WeatherMcpService {

    private WeatherApplicationService weatherApplicationService;

    @Tool(description = "获取指定城市的天气信息")
    public String getWeather(String cityName) {
        return weatherApplicationService.getWeather(cityName);
    }
}
