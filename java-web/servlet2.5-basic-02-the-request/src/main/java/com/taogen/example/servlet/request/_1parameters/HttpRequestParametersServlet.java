package com.taogen.example.servlet.request._1parameters;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpRequestParametersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
        // Do required initialization
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        StringBuilder result = new StringBuilder("This is HTTP Get result page. \nYour parameter name is ");
        String name = request.getParameter("name");
        result.append(name);
        out.println("<h1>" + result.toString() + "</h1>");
    }

    /**
     * The following are the conditions that must be met before post form data will
     * be populated to the parameter set: 1. The request is an HTTP or HTTPS
     * request. 2. The HTTP method is POST. 3. The content type is
     * application/x-www-form-urlencoded . 4. The servlet has made an initial call
     * of any of the getParameter family of methods on the request object. If the
     * conditions are not met and the post form data is not included in the
     * parameter set, the post data must still be available to the servlet via the
     * request object’s input stream. If the conditions are met, post form data will
     * no longer be available for reading directly from the request object’s input
     * stream.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        StringBuilder result = new StringBuilder("This is HTTP Post result page. \nYour parameter name is ");
        String name = getParameterValue("name", request);

        result.append(name);
        out.println("<h1>" + result.toString() + "</h1>");
    }

    public String getParameterValue(String key, HttpServletRequest request) {
        String value = null;
        String requestContentType = request.getContentType();
        System.out.println("Your request contentType is : " + requestContentType);

        StringBuilder sb = new StringBuilder();
        String line = null;
        if ("application/x-www-form-urlencoded".equals(requestContentType)) {
            value = request.getParameter(key);
        } else {
            try (BufferedReader reader = request.getReader();) {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                System.out.println("Your json parameter is : " + sb.toString());
                JSONObject jsonObject = new JSONObject(sb.toString());
                value = jsonObject.getString(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        StringBuilder result = new StringBuilder("This is HTTP Put result page. \nYour parameter name is ");
        String name = request.getParameter("name");
        result.append(name);
        out.println("<h1>" + result.toString() + "</h1>");
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        StringBuilder result = new StringBuilder("This is HTTP Delete result page. \nYour parameter name is ");
        String name = request.getParameter("name");
        result.append(name);
        out.println("<h1>" + result.toString() + "</h1>");
    }

    public void destroy() {
        // do nothing.
    }
}