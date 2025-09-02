package com.iokays.sample.config.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;

@Component
@Endpoint(id = "myCustomInfo") // 定义端点ID，访问路径为 /actuator/myCustomInfo
public class MyCustomEndpoint {

    @ReadOperation // 定义一个GET请求操作
    public Map<String, Object> myCustomData() {
        Map<String, Object> data = new HashMap<>();
        data.put("appName", "Spring Boot Actuator Sample");
        data.put("developer", "iokays");
        data.put("timestamp", System.currentTimeMillis());
        return data;
    }

    // @WriteOperation, @DeleteOperation 还可以定义POST, DELETE操作
}
