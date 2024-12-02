package com.example.demo.core.handlerexception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.core.customexception.BadRequestException;


@ControllerAdvice
public class BadRequestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> exceptionHandler(){
        return ResponseEntity.badRequest().build();
    }
}
