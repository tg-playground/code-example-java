package com.taogen.demo.springbootcrud.core.exceptionhandling.handler;

import com.taogen.demo.springbootcrud.core.exceptionhandling.exception.KnownException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taogen
 */
@ControllerAdvice
@RestController
public class SystemExceptionHandler {

    @ExceptionHandler(value = KnownException.class)
    public ResponseEntity<Map<String, Object>> handleKnownException(KnownException exception) {
        Map<String, Object> result = new HashMap<>();
        result.put("errorMessage", exception.getMessage());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, Object>> handleUnknownException(Exception exception) {
        Map<String, Object> result = new HashMap<>();
        result.put("errorMessage", "系统内部异常");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
