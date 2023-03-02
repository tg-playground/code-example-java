package com.taogen.example.servlet.request._4request_path;

import com.taogen.example.servlet.request.util.ServletHelp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PathTranslationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Real Path
        // returns a String representation of a file on the local file system to which a path corresponds.
        String realPath = getServletContext().getRealPath("/");
        logger.info("Real root path is {}", realPath);
        String requestPathTranslated = req.getPathTranslated();
        logger.info("Path translated is {}", requestPathTranslated);

        resp.setContentType("text/html");
        Map<String, String> paths = new HashMap<>();
        paths.put("realPath", realPath);
        paths.put("requestPathTranslated", requestPathTranslated);
        ServletHelp.writeHtml(getClass().getSimpleName(), resp, paths.toString());
    }
}
