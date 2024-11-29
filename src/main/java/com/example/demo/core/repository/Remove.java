package com.example.demo.core.repository;

import com.example.demo.domain.EntityBase;

public interface Remove<T extends EntityBase,ID> extends Get<T,ID> {
    default void remove(T entity){
        getData().remove(entity);
    }
}
