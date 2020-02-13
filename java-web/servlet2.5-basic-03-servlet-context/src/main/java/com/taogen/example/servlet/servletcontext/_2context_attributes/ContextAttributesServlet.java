package com.taogen.example.servlet.servletcontext._2context_attributes;

import com.taogen.example.servlet.servletcontext.util.ServletHelp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextAttributesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().setAttribute("name", "Taogen");
        String name = (String) getServletContext().getAttribute("name");
        logger.info("servlet context attributes: name is {}", name);

        resp.setContentType("text/html");
        ServletHelp.writeHtml(getClass().getSimpleName(), resp, "servlet context attributes: name is " + name);
    }
}
