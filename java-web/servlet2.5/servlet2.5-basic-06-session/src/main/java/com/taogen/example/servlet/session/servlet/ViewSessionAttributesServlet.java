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
import java.util.*;

public class ViewSessionAttributesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> sessionMap = new HashMap<>();
        HttpSession session = req.getSession();
        Enumeration sessionNames = session.getAttributeNames();
        if (sessionNames != null){
            while(sessionNames.hasMoreElements()){
                String sessionName = (String) sessionNames.nextElement();
                logger.debug("session::{{}: {}}", sessionName, session.getAttribute(sessionName));
                sessionMap.put(sessionName, session.getAttribute(sessionName));
            }
        }else{
            logger.debug("session is null");
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>session attributes: <br>");
        out.println(sessionMap.toString());
        out.println("</h3>");
    }
}
