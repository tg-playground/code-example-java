package com.taogen.example.servlet.filter.filter.examples;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    private ServletContext context;
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.encoding = filterConfig.getInitParameter("encoding");
        logger.debug("CharacterEncodingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("******************************************************");
        logger.debug("CharacterEncodingFilter Begin...");
        if (encoding != null){
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
        logger.debug("CharacterEncodingFilter End.");
        logger.debug("******************************************************");
    }

    @Override
    public void destroy() {

    }
}
