package com.taogen.example.servlet.session.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        logger.debug("SessionFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("SessionFilter Begin...");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (session.isNew()) {
            // New session? OK, redirect to encoded URL with jsessionid in it (and implicitly also set cookie).
            String redirectUri = res.encodeRedirectURL(req.getRequestURI());
            logger.debug("session is new");
            logger.debug("redirect uri is {}", redirectUri);
            res.sendRedirect(redirectUri);
            return;
        } else if (session.getAttribute("verified") == null) {
            logger.debug("session isn't new");
            // Session has not been verified yet? OK, mark it verified so that we don't need to repeat this.
            session.setAttribute("verified", true);
            if (req.isRequestedSessionIdFromCookie()) {
                logger.debug("sessionId is from cookie");
                // Supports cookies? OK, redirect to unencoded URL to get rid of jsessionid in URL.
                String redirectUri = req.getRequestURI().split(";")[0];
                logger.debug("redirect uri is {}", redirectUri);
                res.sendRedirect(redirectUri);
                return;
            } else {
                logger.debug("sessionId isn't from cookie");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

        logger.debug("SessionFilter End.");
    }

    @Override
    public void destroy() {

    }
}
