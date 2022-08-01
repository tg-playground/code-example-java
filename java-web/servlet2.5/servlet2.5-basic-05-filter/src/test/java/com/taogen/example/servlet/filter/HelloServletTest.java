package com.taogen.example.servlet.filter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class HelloServletTest extends MyServletTest {

    private static final HelloServlet helloServlet = new HelloServlet();

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        this.stringWriter = new StringWriter();
        this.printWriter = new PrintWriter(stringWriter);
        buildResponse(response, this.printWriter);
    }

    @After
    public void closeResources() throws IOException {
        this.stringWriter.flush();
        this.printWriter.flush();
    }

    @BeforeClass
    public static void init() {
        helloServlet.setMessage("Hello World!");
    }

    @Test
    public void doGet() throws IOException, ServletException {
        Map<String, String> params = new HashMap<>();
        buildRequestParams(request, params);
        helloServlet.doGet(request, response);
        String result = stringWriter.getBuffer().toString().trim();
        assertTrue(result.contains("Hello World"));
    }
}

