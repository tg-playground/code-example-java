package com.taogen.example.servlet.filter.filter.examples;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
        logger.debug("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Authentication Filter Begin...");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        logger.debug("Requested Resource::{}", uri);

        HttpSession session = request.getSession(false);

        if (session == null && !(uri.endsWith("html") || uri.endsWith("LoginServlet"))){
            logger.debug("Unauthorized access request");
            response.sendRedirect("login.html");
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        logger.debug("Authentication Filter End.");
    }

    @Override
    public void destroy() {

    }
}
