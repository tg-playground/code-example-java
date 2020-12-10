package com.taogen.demo.springbootcrud.core.web.model;

/**
 * @author Taogen
 */
public class GenericResponseModel<T> extends ResponseModel {
    private static final long serialVersionUID = 7100791756352030649L;
    public static final GenericResponseModel OK = new GenericResponseModel<>(0, "success!");
    private T data;

    private GenericResponseModel() {
    }

    public GenericResponseModel(Integer errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
    public GenericResponseModel(String requestId) {
        this.requestId = requestId;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
