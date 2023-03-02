package com.taogen.example.servlet.webapplications;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

    Logger logger = LogManager.getLogger();
    private String message;

    @Override
    public void init() throws ServletException {
        logger.debug("HelloServlet initialized.");
        message = getServletContext().getInitParameter("message");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("HelloServlet doGet() called!");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>");
        out.println(this.message);
        out.println("</h3>");
    }
}
