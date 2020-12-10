package com.taogen.demo.springbootcrud.core.exceptionhandling.exception;

import com.taogen.demo.springbootcrud.core.exceptionhandling.model.ErrorEnum;

/**
 * @author Taogen
 */
public class KnownExceptionWithEnum extends RuntimeException {
    private ErrorEnum errorEnum;

    public KnownExceptionWithEnum(ErrorEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.errorEnum = errorEnum;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }
}
