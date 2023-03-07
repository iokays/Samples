package com.iokays.web.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class ServletSample implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
