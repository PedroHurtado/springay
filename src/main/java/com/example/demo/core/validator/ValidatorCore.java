package com.example.demo.core.validator;

import java.util.List;

import com.example.demo.core.customexception.BadRequestException;

import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;

public interface ValidatorCore<T>  {
    default void validateOrThrow(T instance){
        ValidationResult result =  validator().validate(instance);
        if(!result.isValid()){
            List<ErrorResponse> errors =  result.getErrors().stream().map(e->new ErrorResponse(e.getField(), e.getMessage())).toList();
            throw new BadRequestException(errors);
        }
    }
    Validator<T> validator();
    
}  

    

    

