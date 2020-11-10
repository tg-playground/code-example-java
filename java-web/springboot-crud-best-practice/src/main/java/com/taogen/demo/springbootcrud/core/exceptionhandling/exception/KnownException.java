package com.taogen.demo.springbootcrud.core.exceptionhandling.exception;

/**
 * @author Taogen
 */
public class KnownException extends RuntimeException {

    public KnownException() {
    }

    public KnownException(String message) {
        super(message);
    }
}
