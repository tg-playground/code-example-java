package com.taogen.example.servlet.filter.servlet;

import com.taogen.example.servlet.filter.cache.UserCache;
import com.taogen.example.servlet.filter.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    private final String userID = "admin";
    private final String password = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("LoginServlet doPost() called!");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (this.userID.equals(username) && this.password.equals(password)) {
            logger.debug("legal user login!");

            // add session
            HttpSession session = request.getSession();
            session.setAttribute("userID", username);
            // set session to expiry in 30 mins
            session.setMaxInactiveInterval(30 * 60);

            // add cookie
            UserCache.userCache.put(username, new User(username));
            response.addCookie(new Cookie("userID", username));

            response.sendRedirect(request.getContextPath() + "/loginSuccess.jsp");
        } else {
            logger.debug("error user login!");
            String errorMessage = "<font color=red>Either user name or password is wrong.</font>";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
