package com.taogen.example.httpclient.javahttpurlconnection.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author Taogen
 */
@Data
@ToString
public class HttpUrlConnectionHttpResponse {
    private Integer statusCode;
    private Map<String, List<String>> headers;
    private String body;
}
