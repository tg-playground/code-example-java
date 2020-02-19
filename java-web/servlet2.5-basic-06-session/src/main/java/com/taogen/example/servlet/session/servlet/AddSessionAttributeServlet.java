package com.taogen.example.servlet.session.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AddSessionAttributeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String value = req.getParameter("value");

        HttpSession session = req.getSession();
        session.setAttribute(name, value);

        // servlet container(Tomcat) set session max inactive interval is : 30min (1800 seconds)
        logger.debug("session max Inactive interval is {}", session.getMaxInactiveInterval());;

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>Add session successfully!</h3>");
    }
}
