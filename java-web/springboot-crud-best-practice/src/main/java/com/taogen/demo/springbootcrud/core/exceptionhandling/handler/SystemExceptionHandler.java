package com.taogen.demo.springbootcrud.core.exceptionhandling.handler;

import com.taogen.demo.springbootcrud.core.exceptionhandling.exception.KnownException;
import com.taogen.demo.springbootcrud.core.web.model.ResponseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Taogen
 */
@ControllerAdvice
@RestController
public class SystemExceptionHandler {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = KnownException.class)
    public ResponseEntity<Map<String, Object>> handleKnownException(HttpServletRequest request,
                                                                    KnownException exception) {
        logger.error("Known Exception", exception);
        Map<String, Object> result = new HashMap<>();
        result.put("errorMessage", exception.getMessage());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseModel handleUnknownException(HttpServletRequest request,
                                                Exception exception) {
        logger.error("Unknown Exception", exception);
        ResponseModel responseModel = new ResponseModel();
        Locale locale = LocaleContextHolder.getLocale();
        String errorMsg = messageSource.getMessage(
                "errorMessage.systemInternalError", null, locale);
        responseModel.setErrorMessage(errorMsg);
        return responseModel;
    }
}
