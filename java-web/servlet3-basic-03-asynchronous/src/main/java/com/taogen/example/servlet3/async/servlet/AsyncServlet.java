package com.taogen.example.servlet3.async.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/async"}, asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("doGet() called.");
        response.setContentType("text/html");
        final AsyncContext asyncContext = request.startAsync();
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                HttpServletResponse resp = (HttpServletResponse) asyncContext.getResponse();
                try {
                    PrintWriter out = resp.getWriter();
                    // simulate complex task
                    Thread.sleep(10000);
                    out.println("Async task is completed!");
                } catch (IOException | InterruptedException e) {
                    logger.error(e.getClass().getName(), e.getMessage(), e);
                }
                asyncContext.complete();
            }
        });
    }
}
