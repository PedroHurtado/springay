package com.example.demo.core.repository;

import com.example.demo.domain.EntityBase;

public interface Add<T extends EntityBase> extends Collection<T> {
    default void add(T entity){
        getData().add(entity);
    }
}
