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

public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        logger.debug("request uri is {}", uri);
        HttpSession session = req.getSession(false);
        String userID = null;
        if (session != null){
            logger.debug("sessionID is {}", session.getId());
            userID = (String) session.getAttribute("userID");
        }else{
            logger.debug("session is null");
        }
        logger.debug("session userID is {}", userID);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body><h3>Hello, ");
        out.println(userID);
        out.println("</h3></body></html>");

    }
}
