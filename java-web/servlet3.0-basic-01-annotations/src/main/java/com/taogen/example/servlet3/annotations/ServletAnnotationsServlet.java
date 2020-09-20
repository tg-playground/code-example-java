package com.taogen.example.servlet3.annotations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("doGet() called.");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>");
        out.print(getInitParameter("foo"));
        out.print(getInitParameter("bar"));
        out.println("</h3>");
    }
}
