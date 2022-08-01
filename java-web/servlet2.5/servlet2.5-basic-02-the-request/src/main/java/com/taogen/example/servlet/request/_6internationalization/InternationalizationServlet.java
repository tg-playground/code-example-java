package com.taogen.example.servlet.request._6internationalization;

import com.taogen.example.servlet.request.util.ServletHelp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

public class InternationalizationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Locale locale = req.getLocale();
        Enumeration<Locale> locales = req.getLocales();
        logger.info("Client locale is {}", locale.toString());
        logger.info("locales ");
        int localesSize = 0;
        while (locales.hasMoreElements()) {
            Locale l = locales.nextElement();
            logger.info("Locale i {}", l.toString());
            localesSize++;
        }
        logger.info("locales size is {}", localesSize);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String result = null;
        if ("en".equals(locale.getLanguage())) {
            result = "Your language is English";
        } else if ("zh".equals(locale.getLanguage())) {
            result = "您客户端的语言是中文";
        } else {
            result = new StringBuilder().append("Your language is ").append(locale.getLanguage()).toString();
        }
        ServletHelp.writeHtml(getClass().getSimpleName(), resp, result);
    }
}
