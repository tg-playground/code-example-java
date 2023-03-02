package com.taogen.example.servlet.filter.servlet;

import com.taogen.example.servlet.filter.cache.UserCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userID = null;
        if (session.getAttribute("userID") != null) {
            userID = (String) session.getAttribute("userID");
        }
        // remove cache
        UserCache.userCache.remove(userID);
        logger.debug("cache userID:{} removed!", userID);

        // remove session
        session.removeAttribute("userID");
        session.setMaxInactiveInterval(1);
        logger.debug("session userID:{} removed!", userID);

        // remove cookie
        Cookie userCookie = new Cookie("userID", "");
        userCookie.setMaxAge(0);
        resp.addCookie(userCookie);
        logger.debug("cookie userID:{} removed!", userID);

        resp.sendRedirect(getServletContext().getContextPath() + "/login.jsp");
    }
}
