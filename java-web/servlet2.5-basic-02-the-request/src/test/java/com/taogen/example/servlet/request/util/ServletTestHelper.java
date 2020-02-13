package com.taogen.example.servlet.request.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static org.mockito.Mockito.when;

public class ServletTestHelper {
    public static void buildRequestParams(HttpServletRequest request, Map<String, String> params) {
        for (String key : params.keySet()) {
            when(request.getParameter(key)).thenReturn(params.get(key));
        }
    }

    public static void buildResponse(HttpServletResponse response, PrintWriter printWriter) throws IOException {
        when(response.getWriter()).thenReturn(printWriter);
    }
}
