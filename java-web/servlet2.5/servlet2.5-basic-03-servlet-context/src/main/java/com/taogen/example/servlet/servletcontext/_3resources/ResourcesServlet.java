package com.taogen.example.servlet.servletcontext._3resources;

import com.taogen.example.servlet.servletcontext.util.ServletHelp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResourcesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String picPath = "/pic/girls001.jpg";
        String picRealPath = getServletContext().getRealPath(picPath);
        logger.info("pic real path is {}", picRealPath);

        if (!new File(picRealPath).exists()) {
            resp.setContentType("text/html");
            ServletHelp.writeHtml(getClass().getSimpleName(), resp, "file not exist");
            return;
        } else {
            responseResources(resp, picPath);
        }
    }

    private void responseResources(HttpServletResponse resp, String picPath) {
        try (
                InputStream inputStream = getServletContext().getResource(picPath).openStream();
//                InputStream inputStream = getServletContext().getResourceAsStream(picPath);
                OutputStream outputStream = resp.getOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            for (int len; (len = inputStream.read(buffer)) > 0; ) {
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            logger.error(e.getClass().getName(), e.getMessage(), e);
        }
    }
}
