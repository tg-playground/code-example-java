package com.taogen.demo.springbootcrud.core.exception;

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
