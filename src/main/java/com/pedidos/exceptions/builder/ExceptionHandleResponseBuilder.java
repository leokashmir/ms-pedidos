package com.pedidos.exceptions.builder;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ExceptionHandleResponseBuilder {

    @Value("${projeto.application.version}")
    private String version;

    public ExceptionHandleResponse getExceptionHandleResponseValid(Integer httpCode, String message){
        return ExceptionHandleResponse.builder()
                .apiVersion(version)
                .error(ErrorHandle.builder()
                        .httpCode(httpCode.toString())
                        .message(message.split(":")[1])
                        .detailedMessage(message)
                        .build())
                .build();
    }
    public ExceptionHandleResponse getExceptionHandleResponse(Integer httpCode, String message){
        return ExceptionHandleResponse.builder()
                .apiVersion(version)
                .error(ErrorHandle.builder()
                        .httpCode(httpCode.toString())
                        .message(message)
                        .detailedMessage(message)
                        .build())
                .build();
    }




}
