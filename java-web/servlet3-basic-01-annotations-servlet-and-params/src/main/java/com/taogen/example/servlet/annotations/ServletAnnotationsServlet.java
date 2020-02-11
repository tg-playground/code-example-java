package com.taogen.example.servlet.annotations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletAnnotationsServlet", value = "/hello", initParams = {
        @WebInitParam(name = "foo", value = "Hello "),
        @WebInitParam(name = "bar", value = "World!")
})
public class ServletAnnotationsServlet extends HttpServlet {
    private static final long serialVersionUID = -3462096228274971485L;

    @Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>");
        out.print(getInitParameter("foo"));
        out.print(getInitParameter("bar"));
        out.println("</h3>");
    }
}
