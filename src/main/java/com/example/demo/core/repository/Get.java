package com.example.demo.core.repository;

import com.example.demo.core.customexception.NotFounException;
import com.example.demo.domain.EntityBase;


public interface Get<T extends EntityBase, ID> extends Collection<T> {
    default T get(ID id) {
               return getData().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    throw new NotFounException();
                });
    }
}
