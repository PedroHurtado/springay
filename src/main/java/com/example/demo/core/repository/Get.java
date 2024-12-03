package com.example.demo.core.repository;

import com.example.demo.core.customexception.NotFounException;
import com.example.demo.domain.EntityBase;


public interface Get<T extends EntityBase, ID> extends Collection<T,ID> {
    default T get(ID id) {
               return getData().findById(id)                
                .orElseThrow(() -> {
                    throw new NotFounException();
                });
    }
}
