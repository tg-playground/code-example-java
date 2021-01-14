package com.taogen.example.cas.crm.hr.controller;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Taogen
 */
@RestController
@RequestMapping
public class TestController {

    @Value("${app.cas.server-logout-url}")
    private String CAS_SERVER_LOGOUT_URL;

    @RequestMapping("/hello")
    public String hello() {
        return "hello " + now();
    }

    @RequestMapping
    public String index(HttpSession session, HttpServletRequest request) {
        request.getParameter("ticket");
        Assertion assertion = (Assertion) session.getAttribute("_const_cas_assertion_");
        String username = "";
        if (assertion != null) {
            username = assertion.getPrincipal().getName();
        }
        return new StringBuilder()
                .append("Hi ")
                .append(username)
                .append("<br>")
                .append("Welcome to HR system! <br>index page ")
                .append(now())
                .append("<br>")
                .append("<a href='/logout'>Logout</a>")
                .toString();
    }

    private static String now(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @RequestMapping("/logout")
    public RedirectView logout(HttpSession session){
        if (session != null) {
            session.invalidate();
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(CAS_SERVER_LOGOUT_URL);
        return redirectView;
    }
}
