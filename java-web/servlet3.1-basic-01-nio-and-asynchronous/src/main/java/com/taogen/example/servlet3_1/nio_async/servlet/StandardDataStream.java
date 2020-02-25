package com.taogen.example.servlet3_1.nio_async.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.InputStream;

public class StandardDataStream implements WriteListener {

    private static final Logger logger = LogManager.getLogger();
    private final InputStream content;
    private final AsyncContext async;
    private final ServletOutputStream out;

    public StandardDataStream(InputStream content, AsyncContext async, ServletOutputStream out) {
        this.content = content;
        this.async = async;
        this.out = out;
    }

    @Override
    public void onWritePossible() throws IOException {
        byte[] buffer = new byte[4096];

        // while we are able to write without blocking
        while (out.isReady()) {
            // read some content into the copy buffer
            int len = content.read(buffer);

            // If we are at EOF then complete
            if (len < 0) {
                async.complete();
                return;
            }

            // write out the copy buffer.
            out.write(buffer, 0, len);
        }

    }

    public void onError(Throwable t) {
        logger.error("Async Error", t);
        async.complete();
    }
}
