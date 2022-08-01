package com.taogen.example.servlet.session.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null){
            session.invalidate();
            logger.debug("Old sessionId is {}", session.getId());
        }
        session = req.getSession(true);
        logger.debug("New sessionId is {}", session.getId());
        String redirectUri = resp.encodeRedirectURL(req.getContextPath() + "/login.jsp");
        resp.sendRedirect(redirectUri);
    }
}
