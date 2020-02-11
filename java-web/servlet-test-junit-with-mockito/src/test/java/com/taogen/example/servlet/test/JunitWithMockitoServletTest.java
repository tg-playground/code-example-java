package com.taogen.example.servlet.test;

import org.junit.Before;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class JunitWithMockitoServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGet() throws IOException, ServletException {
        Map<String, String> params = new HashMap<>();
        params.put("name", "Taogen");
        params.put("hobby", "Computer");
        buildRequestParams(params);
        String result = getResponse();
        assertEquals("Taogen-Computer", result);
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
        JunitWithMockitoServlet myServlet = new JunitWithMockitoServlet();
        myServlet.doGet(request, response);
        return sw.getBuffer().toString().trim();
    }
}