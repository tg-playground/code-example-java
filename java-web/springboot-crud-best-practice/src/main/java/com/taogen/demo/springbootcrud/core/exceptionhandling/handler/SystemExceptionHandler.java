package com.taogen.demo.springbootcrud.core.exceptionhandling.handler;

import com.taogen.demo.springbootcrud.core.exceptionhandling.exception.KnownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Taogen
 */
@ControllerAdvice
@RestController
public class SystemExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = KnownException.class)
    public ResponseEntity<Map<String, Object>> handleKnownException(KnownException exception) {
        Map<String, Object> result = new HashMap<>();
        result.put("errorMessage", exception.getMessage());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, Object>> handleUnknownException(Exception exception) {
        Map<String, Object> result = new HashMap<>();
        Locale locale = LocaleContextHolder.getLocale();
        result.put("errorMessage", messageSource.getMessage(
                "errorMessage.systemInternalError", null, locale));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
