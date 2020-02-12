package com.taogen.example.servlet.request._2attributes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestAttributeResultServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getAttribute("name");
        logger.debug("request attributes: name=" + name);
        req.setAttribute("hobby", "Computer");
        req.getRequestDispatcher("/requestAttribute.jsp").forward(req, resp);
    }
}
