package com.example.demo.core.validator;

import com.example.demo.core.customexception.BadRequestException;

import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;

public interface ValidatorCore<T>  {
    default void validateOrTrhow(T instance){
        ValidationResult result =  validator().validate(instance);
        if(!result.isValid()){
            throw new BadRequestException();
        }
    }
    Validator<T> validator();
    
}  

    

    

