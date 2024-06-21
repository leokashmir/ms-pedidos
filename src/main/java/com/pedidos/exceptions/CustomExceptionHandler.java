package com.pedidos.exceptions;

import com.pedidos.exceptions.builder.ErrorHandle;
import com.pedidos.exceptions.builder.ExceptionHandleResponse;
import com.pedidos.exceptions.builder.ExceptionHandleResponseBuilder;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @Autowired
    private ExceptionHandleResponseBuilder handleResponseBuilder;

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ExceptionHandleResponse> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return new ResponseEntity<>(handleResponseBuilder.getExceptionHandleResponseValid(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionHandleResponse> handleException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(handleResponseBuilder.getExceptionHandleResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(), "O Sistema esta em manutenção no momento") , HttpStatus.SERVICE_UNAVAILABLE);
    }
}