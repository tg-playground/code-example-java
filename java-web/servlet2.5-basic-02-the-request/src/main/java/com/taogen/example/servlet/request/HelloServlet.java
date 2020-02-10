package com.taogen.example.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String message;

    public void init() throws ServletException {
        // Do required initialization
        message = "Hello World! ";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        String result = new StringBuilder(message).append(System.currentTimeMillis()).toString();
        out.println("<h1>" + result + "</h1>");
    }

    public void destroy() {
        // do nothing.
    }
}