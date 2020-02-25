package com.taogen.example.servlet3.annotations.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "test-param", value = "Initialization Paramter")})
public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("RequestLoggingFilter initialized");
        logger.debug("test param is {}", filterConfig.getInitParameter("test-param"));
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("RequestLoggingFilter Begin...");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String remoteAddress = servletRequest.getRemoteAddr();
        String uri = request.getRequestURI();
        String protocol = servletRequest.getProtocol();
        logger.debug("Client IP: {}, Resource File: {}, Protocol: {}", remoteAddress, uri, protocol);

        loggingRequestParams(request);
        loggingRequestHeaders(request);
        loggingCookies(request);
        loggingSession(request);

        filterChain.doFilter(servletRequest, servletResponse);
        logger.debug("RequestLoggingFilter End.");
    }

    private void loggingRequestParams(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            String value = request.getParameter(name);
            paramMap.put(name, value);
        }
        logger.debug("{}::Parameters::{}", request.getRemoteAddr(), paramMap.toString());
    }

    private void loggingRequestHeaders(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = (String) headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headerMap.put(headerName, headerValue);
        }
        logger.debug("{}::Headers::{}", request.getRemoteAddr(), headerMap.toString());
    }

    private void loggingCookies(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
            logger.debug("{}::Cookies::{}", request.getRemoteAddr(), cookieMap.toString());
        }else{
            logger.debug("Cookies is null");
        }
    }

    private void loggingSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null){
            logger.debug("SessionId is {}", session.getId());
        }else{
            logger.debug("Session is null");
        }
    }

    @Override
    public void destroy() {

    }
}
