package com.taogen.example.servlet.filter.filter.Wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class CharResponseWrapper extends HttpServletResponseWrapper {
    private CharArrayWriter output;

    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new CharArrayWriter();
    }


    public String toString() {
        return output.toString();
    }

    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(output);
    }

    @Override
    public ServletOutputStream getOutputStream(){
        return new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                output.write(b);
            }
        };
    }
}
