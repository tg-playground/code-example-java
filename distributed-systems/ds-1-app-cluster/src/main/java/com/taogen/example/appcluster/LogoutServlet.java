package com.taogen.example.appcluster;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Taogen
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>Logout successfully!</h3>");
        out.println("<a href='index.jsp'>Go to login page</a>");
    }
}
