package com.taogen.example.servlet.request._5cookies;

import com.taogen.example.servlet.request.util.ServletHelp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class CookiesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get cookies from request
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            logger.info("cookie [{}={}]", cookieName, cookieValue);
        }

        resp.addCookie(new Cookie("User", "Taogen"));
        resp.addCookie(new Cookie("Date", new Date().toString()));

        deleteCookieByName(resp, "User");

        String result = Arrays.asList(cookies).stream()
                .map(cookie -> new StringBuilder().append(cookie.getName()).append("=").append(cookie.getValue()))
                .collect(Collectors.toList()).toString();
        resp.setContentType("text/html");
        ServletHelp.writeHtml(getClass().getSimpleName(), resp, result);
    }

    private void deleteCookieByName(HttpServletResponse response, String name) {
        // Delete cookies from response
        Cookie deleteCookie = new Cookie(name, "");
        deleteCookie.setMaxAge(0);
        response.addCookie(deleteCookie);
    }
}
