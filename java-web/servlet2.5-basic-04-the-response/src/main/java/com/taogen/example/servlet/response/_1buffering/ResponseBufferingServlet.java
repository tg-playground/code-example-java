package com.taogen.example.servlet.response._1buffering;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseBufferingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // Response Buffer Size
        logger.debug("Response buffer size: {}",resp.getBufferSize());
        // This allows the container to reuse a set of fixed size buffers, providing a larger buffer than requested if appropriate.
        resp.setBufferSize(9000);
        logger.debug("Response buffer size: {}",resp.getBufferSize());

        // Response Commit and Reset
        // First response
        PrintWriter out = resp.getWriter();
        out.println("First Hello!<br>");
        // Reset buffer before commit
        resp.resetBuffer();
        out.println("I am real First Hello!<br>");
        out.println("Waiting 3 seconds...<br>");
        logger.debug("Response isCommit: {}", resp.isCommitted());
        out.flush();
        logger.debug("Response isCommit: {}", resp.isCommitted());
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Next response
        out.println("Second Hello!<br>");
    }
}
