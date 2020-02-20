package com.taogen.example.servlet.dispatching._3forward;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DispatcherForwardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // RequestDispatcher forward: the servlet response content will be clear, these response content don't response to client.
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>I will forward to next Servlet.</h3>");

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/acceptForward");
        requestDispatcher.forward(req, resp);
    }
}
