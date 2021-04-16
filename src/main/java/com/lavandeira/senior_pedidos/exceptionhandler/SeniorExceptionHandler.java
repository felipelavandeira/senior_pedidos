package com.lavandeira.senior_pedidos.exceptionhandler;

import com.lavandeira.senior_pedidos.exceptionhandler.exception.ClosedOrderDiscountException;
import com.lavandeira.senior_pedidos.exceptionhandler.exception.NotFoundException;
import org.apache.catalina.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class SeniorExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFound(Exception exception, WebRequest request) {
        String userMessage = messageSource.getMessage("notfound", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(
                exception,
                new Error(userMessage, Arrays.toString(exception.getStackTrace())),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);
    }

    @ExceptionHandler(value = {ClosedOrderDiscountException.class})
    public ResponseEntity<Object> handleClosedOrderDiscount(ClosedOrderDiscountException exception, WebRequest request) {
        String userMessage = exception.getMessage();
        return handleExceptionInternal(
                exception,
                new Error(userMessage, Arrays.toString(exception.getStackTrace())),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointer(NullPointerException exception, WebRequest request){
        String userMessage = messageSource.getMessage("nullpointer", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(
                exception,
                new Error(userMessage, Arrays.toString(exception.getStackTrace())),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String userMessage = messageSource.getMessage("badrequest", null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(
                ex,
                new Error(userMessage, Arrays.toString(ex.getStackTrace())),
                headers,
                status,
                request);
    }
}
