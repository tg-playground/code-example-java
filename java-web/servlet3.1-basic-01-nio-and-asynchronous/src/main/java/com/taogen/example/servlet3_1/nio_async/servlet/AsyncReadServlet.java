package com.taogen.example.servlet3_1.nio_async.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/async-read"}, asyncSupported = true)
public class AsyncReadServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("doPost() called.");
        logger.debug("Servlet Thread: {}", Thread.currentThread().getName());

        AsyncContext asyncContext = request.startAsync();
        ServletInputStream is = request.getInputStream();
        is.setReadListener(new ReadListener() {
            private int totalReadBytes = 0;
            @Override
            public void onDataAvailable() throws IOException {
                logger.debug("ReadListener thread: " + Thread.currentThread().getName());
                try {
                    byte buffer[] = new byte[1 * 1024];
                    int readBytes = 0;
                    while (is.isReady() && !is.isFinished()) {
                        int length = is.read(buffer);
                        if (length == -1 && is.isFinished()) {
                            asyncContext.complete();
                            logger.debug("Read: {} bytes", readBytes);
                            logger.debug("Total Read: {} bytes", totalReadBytes);
                            return;
                        }
                        readBytes += length;
                        totalReadBytes += length;
                    }
                    logger.debug("Read: {} bytes", readBytes);
                } catch (IOException ex) {
                    logger.error(ex);
                    asyncContext.complete();
                }
            }

            @Override
            public void onAllDataRead() throws IOException {

                try {
                    logger.debug("Total Read: {} bytes", totalReadBytes);
                    asyncContext.getResponse().getWriter().println("Finished");
                } catch (IOException ex) {
                    logger.error(ex);
                }
                asyncContext.complete();
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error("Async Error", throwable);
                asyncContext.complete();
            }
        });

    }
}
