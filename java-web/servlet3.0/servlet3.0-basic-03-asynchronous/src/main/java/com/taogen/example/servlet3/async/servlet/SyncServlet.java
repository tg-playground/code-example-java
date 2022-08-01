package com.taogen.example.servlet3.async.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = {"/sync"})
public class SyncServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("doGet() called.");
        waitForData();
        PrintWriter out  = resp.getWriter();
        out.println("OK");
        out.println(new Date().toString());
    }

    public static void waitForData() {
        try {
//            Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
            Thread.sleep(10000);// 10s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
