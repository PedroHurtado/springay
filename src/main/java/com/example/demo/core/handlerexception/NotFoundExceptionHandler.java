package com.example.demo.core.handlerexception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.core.customexception.NotFounException;

@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(NotFounException.class)
    public ResponseEntity<?> exceptionHandler(){
        return ResponseEntity.notFound().build();
    }
}
