package com.taogen.example.servlet.response._2headers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

public class ResponseHeadersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Set Response Headers
        resp.setIntHeader("responseID", 001);
        resp.setHeader("Version", "1.0.0");
        resp.setDateHeader("responseTime", new Date().getTime());

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("Checking the response headers.<br>Add Headers: version=1.0.0, responseTime=currentTime");
    }
}
