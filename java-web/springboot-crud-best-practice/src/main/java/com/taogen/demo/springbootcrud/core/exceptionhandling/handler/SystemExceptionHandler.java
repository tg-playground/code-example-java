package com.taogen.demo.springbootcrud.core.exceptionhandling.handler;

import com.taogen.demo.springbootcrud.core.exceptionhandling.exception.KnownExceptionWithEnum;
import com.taogen.demo.springbootcrud.core.exceptionhandling.model.ErrorEnum;
import com.taogen.demo.springbootcrud.core.exceptionhandling.model.ErrorMessage;
import com.taogen.demo.springbootcrud.core.web.model.ResponseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;

/**
 * @author Taogen
 */
@RestControllerAdvice
public class SystemExceptionHandler {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private MessageSource messageSource;

    /**
     * Known Exceptions
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = KnownExceptionWithEnum.class)
    public ResponseEntity<ErrorMessage> handleKnownException(HttpServletRequest request,
                                                             KnownExceptionWithEnum exception) {
        logger.error("Known Exception", exception);
        return new ResponseEntity(new ErrorMessage(exception.getErrorEnum()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleIntegrityViolationException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorMessage(ErrorEnum.METHOD_NOT_ALLOWED,
                Arrays.asList(new StringBuilder()
                        .append(exception.getClass().getName())
                        .append(": ")
                        .append(exception.getMessage())
                        .toString())), HttpStatus.BAD_REQUEST);
    }

    /**
     * HttpRequestMethodNotSupportedException
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorMessage> handleHttpRequestMethodNotSupportedException(
            HttpServletRequest request,
            HttpRequestMethodNotSupportedException exception) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorMessage(ErrorEnum.METHOD_NOT_ALLOWED,
                Arrays.asList(new StringBuilder()
                        .append(exception.getClass().getName())
                        .append(": ")
                        .append(exception.getMessage())
                        .toString())), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Unknown Exceptions
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorMessage> handleUnknownException(HttpServletRequest request,
                                                               Exception exception) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorMessage(ErrorEnum.SYSTEM_ERROR,
                Arrays.asList(new StringBuilder()
                        .append(exception.getClass().getName())
                        .append(": ")
                        .append(exception.getMessage())
                        .append(". ")
                        .append(Arrays.asList(exception.getStackTrace()))
                        .toString())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getI18nErrorMsg() {
        Locale locale = LocaleContextHolder.getLocale();
        String errorMsg = messageSource.getMessage(
                "errorMessage.systemInternalError", null, locale);
        return errorMsg;
    }
}
