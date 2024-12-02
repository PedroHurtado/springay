package com.example.demo.core.customexception;

import java.util.List;

import com.example.demo.core.validator.ErrorResponse;

public class BadRequestException extends RuntimeException {
    private final List<ErrorResponse> errors;
    public BadRequestException(List<ErrorResponse> errors){
        this.errors = errors;
    }
    public List<ErrorResponse> getErrors(){
        return errors;
    }
}
