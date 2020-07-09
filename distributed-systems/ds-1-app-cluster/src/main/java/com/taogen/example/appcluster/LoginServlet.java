package com.taogen.example.appcluster;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Taogen
 */
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("access LoginServlet!");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || password == null || ! password.equals(username)) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        req.getSession().setAttribute("user", username);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>Login successfully!</h3>");
        out.println("<a href='home.jsp'>Go to home page</a>");
    }
}
