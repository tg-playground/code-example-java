package com.taogen.example.servlet.request._0methods;

import com.taogen.example.servlet.request.util.ServletHelp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpRequestMethodsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String result = "This is HTTP Get result page";
        ServletHelp.writeHtml(getClass().getSimpleName(), response, result);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String result = "This is HTTP Post result page";
        ServletHelp.writeHtml(getClass().getSimpleName(), response, result);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String result = "This is HTTP Put result page";
        ServletHelp.writeHtml(getClass().getSimpleName(), response, result);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String result = "This is HTTP Delete result page";
        ServletHelp.writeHtml(getClass().getSimpleName(), response, result);
    }

    @Override
    public void destroy() {
        // do nothing.
    }
}