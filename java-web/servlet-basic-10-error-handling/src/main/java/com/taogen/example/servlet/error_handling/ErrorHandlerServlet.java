package com.taogen.example.servlet.error_handling;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.servlet.RequestDispatcher.*;

@WebServlet(urlPatterns = "/errorHandler")
public class ErrorHandlerServlet extends HttpServlet {
 
    private static final long serialVersionUID = 1L;

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