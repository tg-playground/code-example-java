package com.taogen.example.servlet.request._2attributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestAttributeResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getAttribute("name");
        System.out.println("request attributes: name=" + name);
        req.setAttribute("hobby", "Computer");
        req.getRequestDispatcher("/requestAttribute.jsp").forward(req, resp);
    }
}
