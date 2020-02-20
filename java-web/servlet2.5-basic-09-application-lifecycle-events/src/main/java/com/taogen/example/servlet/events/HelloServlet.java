package com.taogen.example.servlet.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("HelloServlet doGet() called!");
        HttpSession session = req.getSession();
        session.setAttribute("message", "Hello");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>Hello</h3>");
    }
}
