package com.taogen.example.servlet.filter.filter.examples;

import com.taogen.example.servlet.filter.cache.UserCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        String sessionUserID = getSessionUserID(request);
        logger.debug("sessionUserID is {}", sessionUserID);
        String cookieUserID = getCookieUserID(request);
        logger.debug("cookieUserID is {}", cookieUserID);
        if ((sessionUserID == null || cookieUserID == null || !sessionUserID.equals(cookieUserID) || UserCache.userCache.get(sessionUserID) == null)
                && !(uri.endsWith("login.jsp") || uri.endsWith("/login"))) {
            logger.debug("Unauthorized access request");
            response.sendRedirect(servletContext.getContextPath() + "/login.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        logger.debug("Authentication Filter End.");
    }

    private String getSessionUserID(HttpServletRequest request) {
        String sessionUserID = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            sessionUserID = (String) session.getAttribute("userID");
        }
        return sessionUserID;
    }

    private String getCookieUserID(HttpServletRequest request) {
        String cookieUserID = null;
        if (request.getCookies() != null) {
            List<Cookie> cookieList = Arrays.asList(request.getCookies());
            if (cookieList != null) {
                for (Cookie cookie : cookieList) {
                    if ("userID".equals(cookie.getName())) {
                        cookieUserID = cookie.getValue();
                    }
                }
            }
        }
        return cookieUserID;
    }

    @Override
    public void destroy() {

    }
}
