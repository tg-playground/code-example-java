package com.taogen.example.servlet.error_handling;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class ErrorHandlerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";
    private static final String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    private static final String ERROR_MESSAGE = "javax.servlet.error.message";

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html><head><title>Error description</title></head><body>");
            writer.write("<h2>Error description</h2>");
            writer.write("<ul>");
            Arrays.asList(
                    ERROR_STATUS_CODE,
                    ERROR_EXCEPTION_TYPE,
                    ERROR_MESSAGE)
                    .forEach(attributeName ->
                            writer.write("<li>" + attributeName + ":" + req.getAttribute(attributeName) + " </li>")
                    );
            writer.write("</ul>");
            writer.write("</html></body>");
        }
    }
}