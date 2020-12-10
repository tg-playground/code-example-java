package com.taogen.demo.springbootcrud.core.exceptionhandling.handler;

import com.taogen.demo.springbootcrud.core.exceptionhandling.model.ErrorEnum;
import com.taogen.demo.springbootcrud.core.exceptionhandling.model.ErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JPA Data Validation Exception Handler
 *
 * @author Taogen
 */
@RestControllerAdvice
public class GlobalControllerDataValidationExceptionHandler {
    private static final Logger logger = LogManager.getLogger();

    /**
     * 400: BAD_REQUEST
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        logger.error("Bad request", ex);
        ErrorMessage errorMessage = new ErrorMessage(ErrorEnum.BAD_REQUEST);
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.add(fieldName + message);
        });
        errorMessage.setErrors(errors);
        return errorMessage;
    }
}
