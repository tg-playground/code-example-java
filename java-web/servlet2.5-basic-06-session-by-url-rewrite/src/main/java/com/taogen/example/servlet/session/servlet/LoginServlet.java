package com.taogen.example.servlet.session.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
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
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            logger.debug("Cookies size is {}", cookies.length);
        }else{
            logger.debug("Cookies is null");
        }

        if (this.username.equals(username) && this.password.equals(password)) {
            HttpSession session = req.getSession();
            logger.debug("sessionID is {}", session.getId());
            session.setAttribute("userID", username);
            session.setMaxInactiveInterval(30*60);
            String encodeUrl = resp.encodeRedirectURL(req.getContextPath() + "/loginSuccess.jsp");
            logger.debug("encodeUrl is {}", encodeUrl);
//            resp.sendRedirect(encodeUrl);
//            req.getRequestDispatcher("/loginSuccess.jsp").forward(req, resp);
            req.getRequestDispatcher("/loginSuccess.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage","<font color=red>Either user name or password is wrong.</font>");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }

    }
}
