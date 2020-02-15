package com.taogen.example.servlet.response._3redirect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseRedirectErrorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = getServletContext().getContextPath();
        logger.debug("contextPath is {}", contextPath);
//        resp.sendError(404, "Your request is Not Found!");
        resp.sendError(500, "server has internal error!");
    }

}
