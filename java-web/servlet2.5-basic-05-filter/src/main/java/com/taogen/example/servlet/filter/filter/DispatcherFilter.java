package com.taogen.example.servlet.filter.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class DispatcherFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
        logger.debug("DispatcherFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.debug("Dispatcher Filter Begin...");

        filterChain.doFilter(servletRequest, servletResponse);
        logger.debug("Dispatcher Filter End.");
    }

    @Override
    public void destroy() {

    }
}
