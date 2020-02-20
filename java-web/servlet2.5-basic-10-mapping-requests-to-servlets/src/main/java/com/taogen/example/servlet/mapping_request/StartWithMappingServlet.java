package com.taogen.example.servlet.mapping_request;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StartWithMappingServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("StartWithMappingServlet doGet() called.");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(new StringBuilder().append("<h3>Your request uri is ").append(req.getRequestURI()).append("</h3>"));
        out.println("<br>servletName: " + getServletName());
    }
}
