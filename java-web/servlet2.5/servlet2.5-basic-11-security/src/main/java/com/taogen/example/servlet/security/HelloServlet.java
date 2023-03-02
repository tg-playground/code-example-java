package com.taogen.example.servlet.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

public class HelloServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("HelloServlet doGet() called.");
        String remoteUser = req.getRemoteUser();
        boolean isUserInRole = req.isUserInRole(remoteUser);
        Principal userPrincipal = req.getUserPrincipal();
        logger.debug("remoteUser is {}", remoteUser);
        logger.debug("isUserInRole: {}", isUserInRole);
        logger.debug("userPrincipal is {}", userPrincipal);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>Hello</h3>");
    }
}
