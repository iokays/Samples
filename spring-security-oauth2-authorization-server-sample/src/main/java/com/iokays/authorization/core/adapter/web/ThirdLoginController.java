package com.iokays.authorization.core.adapter.web;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.UUID;

/**
 * 因为看到太多的, 直接使用浏览器等工具直接访问Oauth2服务器.
 * <p>
 * 这个是第三方的Oauth2 自定义客户端, 偷懒而已, 不想启动另一个应用服务
 */
@Slf4j
@RequestMapping("/third")
@RestController
@AllArgsConstructor
public class ThirdLoginController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String clientId = "login-client";
    private final String clientSecret = "secret";
    private final String baseUrl = "http://localhost:8080";
    private final String redirectUrl = "http://localhost:8080/third/callback";

    private final HttpServletResponse response; //注入

    @GetMapping("/authorization")
    public void authorization() throws IOException {
        final var state = UUID.randomUUID().toString().replace("-", "");
        final String url = "%s/oauth2/authorize?client_id=%s&response_type=code&state=%s&redirect_uri=%s"
                .formatted(this.baseUrl, this.clientId, state, this.redirectUrl);
        response.sendRedirect(url);
    }


    /**
     * @param code
     * @param state
     * @see AuthorizationServerSettings.builder()
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(value = "code") String code, @RequestParam("state") String state) {
        final var url = this.baseUrl + "/oauth2/token";

        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(this.clientId, this.clientSecret); // 更安全的设置方式

        // 2. 设置请求参数（必须包含redirect_uri）
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("code", code);
        params.add("redirect_uri", this.redirectUrl); // 必须与授权请求时完全一致

        final var requestEntity = new HttpEntity<>(params, headers);

        return restTemplate.postForObject(url, requestEntity, String.class);

    }

}
