package com.example.demo.core.repository;

import com.example.demo.core.customexception.NotFounException;

public interface Get<T,ID> extends Collection<T> {
    default T get(ID id){
        return getData().stream()
            .filter(i->i.equals(id))
            .findFirst()
            .orElseThrow(()->{
                throw new NotFounException();
            });
    }
}
