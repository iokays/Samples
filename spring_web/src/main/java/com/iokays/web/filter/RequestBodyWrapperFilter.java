package com.iokays.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestBodyWrapperFilter extends OncePerRequestFilter implements OrderedFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestBodyWrapperFilter.class);

    private static final String JSON_CONTENT_TYPE = "application/json";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.info("执行前置设置过滤器， 并封装RequestBody。");
        filterChain.doFilter(new BodyMultipleTimesReaderRequestWrapper(request), response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !isRequestBody(request);
    }
    private boolean isRequestBody(final HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("POST")
                && StringUtils.isNotBlank(request.getContentType())
                && request.getContentType().contains(JSON_CONTENT_TYPE);
    }

    @Override
    public int getOrder() {
        return FilterOrderEnum.REQUEST_BODY.ordinal();
    }
}





