package com.taogen.example.httpclient.jetty;

import com.taogen.example.httpclient.BaseTest;
import org.eclipse.jetty.client.api.ContentResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JettyHttpClientUtilTest extends BaseTest {

    @Test
    void requestWithoutBody() throws ExecutionException, InterruptedException, TimeoutException {
//        ContentResponse result = JettyHttpClientUtil.requestWithoutBody(userEndpointUrl, HttpMethod.GET,
//                getBasicParam(), getBasicHeaders());
//        System.out.println(result.getContentAsString());
//        assertEquals(HttpStatus.OK.value(), result.getStatus());
//        assertNotNull(result.getContentAsString());
//        assertTrue(result.getContentAsString().contains("\"id\":1"));
//        ContentResponse result2 = JettyHttpClientUtil.requestWithoutBody(userEndpointUrl + "/1", HttpMethod.GET,
//                getBasicParam(), getBasicHeaders());
//        System.out.println(result2.getContentAsString());
//        assertEquals(HttpStatus.OK.value(), result2.getStatus());
//        assertNotNull(result2.getContentAsString());
//        assertTrue(result2.getContentAsString().contains("\"id\":1"));
    }

    @Test
    void requestWithBody() {
    }

    @Test
    void requestWithForm() {
    }

    @Test
    void requestWithMultipart() {
    }
}
