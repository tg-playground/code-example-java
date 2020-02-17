package com.taogen.example.servlet.session.session_tracking_by_url_rewriting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    private String username = "admin";
    private String password = "password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("LoginServlet doPost() called!");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (this.username.equals(username) && this.password.equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("userID", username);
            session.setMaxInactiveInterval(30*60);
            String encodeUrl = resp.encodeRedirectURL(req.getContextPath() + "/loginSuccess");
            String encodeUrl2 = resp.encodeURL(req.getContextPath() + "/loginSuccess");
            logger.debug("encodeUrl is {}", encodeUrl);
            logger.debug("encodeUrl2 is {}", encodeUrl2);
            resp.sendRedirect(encodeUrl);
        } else {
            resp.getWriter().println("<font color=red>Either user name or password is wrong.</font>");
            req.getRequestDispatcher("/login.html").include(req, resp);
        }

    }
}
