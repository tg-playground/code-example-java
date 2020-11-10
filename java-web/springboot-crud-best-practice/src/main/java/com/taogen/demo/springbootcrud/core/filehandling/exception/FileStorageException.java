package com.taogen.demo.springbootcrud.core.filehandling.exception;

import com.taogen.demo.springbootcrud.core.exceptionhandling.exception.KnownException;

/**
 * @author Taogen
 */
public class FileStorageException extends KnownException {
    public FileStorageException() {
    }

    public FileStorageException(String message) {
        super(message);
    }

}
