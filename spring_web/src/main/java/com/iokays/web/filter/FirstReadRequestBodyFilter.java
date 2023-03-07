package com.iokays.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FirstReadRequestBodyFilter extends OncePerRequestFilter implements OrderedFilter {

    private static final Logger logger = LoggerFactory.getLogger(FirstReadRequestBodyFilter.class);


    @Resource
    private ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.info("执行前置业务过滤器， 并读取 RequestBody: {}", new String(request.getInputStream().readAllBytes()));
        filterChain.doFilter(request, response);
    }

    @Override
    public int getOrder() {
        return FilterOrderEnum.REQUEST_BODY.ordinal() + 1;
    }
}





