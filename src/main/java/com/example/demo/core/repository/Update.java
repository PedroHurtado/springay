package com.example.demo.core.repository;

import com.example.demo.domain.EntityBase;

public interface Update<T extends EntityBase,ID> extends Get<T,ID> {
    default void update(T entity){
       getData().save(entity);
    }
}
