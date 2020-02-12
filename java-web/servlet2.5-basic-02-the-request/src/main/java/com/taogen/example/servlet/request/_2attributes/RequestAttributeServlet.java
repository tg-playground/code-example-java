package com.taogen.example.servlet.request._2attributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestAttributeServlet extends HttpServlet {
    /**
     * Attributes are objects associated with a request. Pass key/value between Servlets or JSP.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name", "Tom");
        req.getRequestDispatcher("/requestAttributeResult").forward(req, resp);
    }
}
