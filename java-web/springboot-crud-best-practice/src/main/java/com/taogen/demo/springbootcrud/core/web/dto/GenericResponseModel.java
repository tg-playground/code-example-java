package com.taogen.demo.springbootcrud.core.web.dto;

/**
 * @author Taogen
 */
public class GenericResponseModel<T> extends ResponseModel {
    private static final long serialVersionUID = 7100791756352030649L;
    private T responseBody;

    private GenericResponseModel() {
    }

    public GenericResponseModel(String requestId) {
        this.requestId = requestId;
    }

    public T getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }
}
