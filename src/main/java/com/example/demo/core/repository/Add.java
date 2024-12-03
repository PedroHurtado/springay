package com.example.demo.core.repository;

import com.example.demo.domain.EntityBase;

public interface Add<T extends EntityBase, ID> extends Collection<T,ID> {
    default void add(T entity){
        getData().save(entity);
    }
}
