package com.taogen.example.servlet.request._3headers;

import com.taogen.example.servlet.request.util.ServletHelp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestHeadersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("RequestHeadersServlet doGet() called!");

        logger.info("headers begin <<<");
        Map<String, String> headers = new HashMap<>();
        Enumeration<?> headersEnum = req.getHeaderNames();
        if (headersEnum != null) {
            while (headersEnum.hasMoreElements()) {
                String headerName = headersEnum.nextElement().toString();
                logger.info("{} : {}", headerName, req.getHeader(headerName));
                headers.put(headerName, req.getHeader(headerName));
            }
        }
        logger.info(">>> headers end.");
        resp.setContentType("text/html");
        String result = new StringBuilder().append("Your headers are: <br>").append(headers.toString()).toString();
        ServletHelp.writeHtml(getClass().getSimpleName(), resp, result);
    }
}
