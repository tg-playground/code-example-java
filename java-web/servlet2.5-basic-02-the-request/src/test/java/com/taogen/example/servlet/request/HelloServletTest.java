package com.taogen.example.servlet.request;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class HelloServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private static final HelloServlet helloServlet = new HelloServlet();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void init(){
        helloServlet.setMessage("Hello World!");
    }

    @Test
    public void doGet() throws IOException, ServletException {
        Map<String, String> params = new HashMap<>();
        buildRequestParams(params);
        String result = getResponse();
        assertTrue(result.contains("Hello World"));
    }

    private void buildRequestParams(Map<String, String> params) {
        for (String key : params.keySet()) {
            when(request.getParameter(key)).thenReturn(params.get(key));
        }
    }

    private String getResponse() throws IOException, ServletException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        helloServlet.doGet(request, response);
        return sw.getBuffer().toString().trim();
    }
}
