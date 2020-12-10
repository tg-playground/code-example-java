package com.taogen.demo.springbootcrud.core.exceptionhandling.model;

/**
 * @author Taogen
 */
public enum ErrorEnum {
    /**
     * Server error (未知系统异常)，返回状态码 500
     */
    SYSTEM_ERROR("INTERNAL_SYSTEM_ERROR", "系统内部错误"),

    /**
     * Client error (请求错误，已知业务异常)，返回状态码 400~499
     */
    BAD_REQUEST("BAD_REQUEST", "错误的请求"),

    PARAM_ERROR("PARAM_ERROR", "参数错误"),
    ;

    private String errorCode;
    private String errorMessage;

    ErrorEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorEnum{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
