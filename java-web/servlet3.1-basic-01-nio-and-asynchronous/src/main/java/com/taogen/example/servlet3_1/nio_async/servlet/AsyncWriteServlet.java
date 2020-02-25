package com.taogen.example.servlet3_1.nio_async.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns = {"/async-write"}, asyncSupported = true)
public class AsyncWriteServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("doGet() called.");
        logger.debug("Servlet thread: " + Thread.currentThread().getName());
        AsyncContext asyncCtx = req.startAsync();
        ServletOutputStream os = resp.getOutputStream();
        InputStream bigfileInputStream = getServletContext().getResourceAsStream("/bigfile");
        os.setWriteListener(new WriteListener() {
            @Override
            public void onWritePossible() throws IOException {
                int loopCount = 0;
                logger.debug("WriteListener thread: {}", Thread.currentThread().getName());
                while (os.isReady()) {
                    loopCount++;
                    logger.debug("Loop Count: {}", loopCount);
                    byte[] bytes = readContent();
                    if (bytes != null) {
                        os.write(bytes);
                    } else {
                        closeInputStream();
                        asyncCtx.complete();
                        break;
                    }
                }
            }

            @Override
            public void onError(Throwable t) {

                try {
                    os.print("Error happened");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeInputStream();
                    asyncCtx.complete();
                }
            }

            private byte[] readContent() throws IOException {
                byte[] bytes = new byte[1024];
                int readLength = bigfileInputStream.read(bytes);
                if (readLength <= 0) {
                    return null;
                }
                return bytes;
            }

            private void closeInputStream() {
                try {
                    bigfileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
