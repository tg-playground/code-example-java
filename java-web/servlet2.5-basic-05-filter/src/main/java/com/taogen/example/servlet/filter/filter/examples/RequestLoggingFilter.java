package com.taogen.example.servlet.filter.filter.examples;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        logger.debug("RequestLoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Logging Filter Begin...");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String remoteAddress = servletRequest.getRemoteAddr();
        String uri = request.getRequestURI();
        String protocol = servletRequest.getProtocol();
        logger.info("Client IP: {}, Resource File: {}, Protocol: {}", remoteAddress, uri, protocol);

        // Params
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            String value = request.getParameter(name);
            logger.debug("{}::Request Params::{{}, {}}", request.getRemoteAddr(), name, value);
        }

        // Cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                logger.debug("{}::Cookie::{{}, {}}", request.getRemoteAddr(), cookie.getName(), cookie.getValue());
            }
        }

        // headers
//        Enumeration headerNames = request.getHeaderNames();
//        while(headerNames.hasMoreElements()){
//            String headerName = (String) headerNames.nextElement();
//            String headerValue = request.getHeader(headerName);
//            logger.debug("{}::Header::{{}, {}}", request.getRemoteAddr(), headerName, headerValue);
//        }

        filterChain.doFilter(servletRequest, servletResponse);
        logger.debug("Logging Filter End.");
    }

    @Override
    public void destroy() {

    }
}
