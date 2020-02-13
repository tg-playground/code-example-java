package com.taogen.example.servlet.request._4request_path;

import com.taogen.example.servlet.request.util.ServletHelp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet mapping by /requestPath/*
 */
public class RequestPathServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Request URI
        logger.debug("RequestPathServlet called!");
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String pathInfo = req.getPathInfo();
        String requestUri = req.getRequestURI();
        logger.info("request URI is {}", requestUri);
        logger.info("request URI = {contextPath: {} + servletPath: {} + pathInfo: {}", contextPath, servletPath, pathInfo);

        resp.setContentType("text/html");
        Map<String, String> paths = new HashMap<>();
        paths.put("requestUri", requestUri);
        paths.put("contextPath", contextPath);
        paths.put("servletPath", servletPath);
        paths.put("pathInfo", pathInfo);
        ServletHelp.writeHtml(getClass().getSimpleName(), resp, paths.toString());
    }
}
