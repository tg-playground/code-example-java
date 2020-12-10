package com.taogen.demo.springbootcrud.core.exceptionhandling.model;

import java.util.List;

/**
 * @author Taogen
 */
public class ErrorMessage {
    private String code;
    private String message;
    private List<String> errors;

    public ErrorMessage(){}

    public ErrorMessage(ErrorEnum errorEnum) {
        this.code = errorEnum.getErrorCode();
        this.message = errorEnum.getErrorMessage();
    }

    public ErrorMessage(ErrorEnum errorEnum, List<String> errors) {
        this.code = errorEnum.getErrorCode();
        this.message = errorEnum.getErrorMessage();
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
