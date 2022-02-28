package com.taogen.example.httpclient.springrestclient;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @author Taogen
 */
@Component
@RequiredArgsConstructor
public class SpringRestTemplateUtil {
    private final RestTemplate restTemplate;

    /**
     * Get, post, put, delete
     *
     * @param url
     * @param method
     * @param params
     * @param headers
     * @param body    when request content-type is application/json, body is string object.
     *                when request content-type is application/x-www-form-urlencoded, body is MultiValueMap<String, String> object.
     * @return String object or entity object
     */
    public <T> ResponseEntity<T> request(String url,
                                         HttpMethod method,
                                         MultiValueMap<String, String> params,
                                         MultiValueMap<String, String> headers,
                                         Object body,
                                         Class<T> responseType) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParams(params);
        HttpEntity<Object> requestEntity =
                new HttpEntity<>(body, headers);
        return restTemplate.exchange(builder.toUriString(), method, requestEntity, responseType);
    }

    /**
     * Get object list
     *
     * @param url
     * @param params
     * @param headers
     * @param body
     * @param responseType
     * @param <T>
     * @return entity list or string list
     */
    public <T> ResponseEntity<List<T>> getObjectList(String url,
                                                     MultiValueMap<String, String> params,
                                                     MultiValueMap<String, String> headers,
                                                     Object body,
                                                     ParameterizedTypeReference<List<T>> responseType) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParams(params);
        HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, responseType);
    }
}
