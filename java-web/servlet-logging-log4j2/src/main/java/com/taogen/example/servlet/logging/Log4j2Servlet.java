package com.taogen.example.servlet.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class Log4j2Servlet extends HttpServlet {
    private final static Logger log = LogManager.getLogger(Log4j2Servlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Log4j2Servlet's doGet() called");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("Hello by Log4j2Servlet!");
    }
}
