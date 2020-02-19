package com.taogen.example.servlet.session.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateNewSessionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null){
            logger.debug("Old sessionId is {}", session.getId());
            logger.debug("Old session create time is {}", session.getCreationTime());
            session.invalidate();
        }
        session = req.getSession();
        logger.debug("New sessionId is {}", session.getId());

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>new session has created!</h3>");
    }
}
