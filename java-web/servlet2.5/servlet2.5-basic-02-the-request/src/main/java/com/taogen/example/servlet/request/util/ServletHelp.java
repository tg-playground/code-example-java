package com.taogen.example.servlet.request.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletHelp {
    public static final String RESPONSE_HTML_TEMPLATE = "<html><head><title>%s</title></head><body><h3>%s</h3></body></html>";

    public static void writeHtml(String className, HttpServletResponse response, String result) throws IOException {
        PrintWriter out = response.getWriter();
        out.format(RESPONSE_HTML_TEMPLATE, className, result);
    }
}
