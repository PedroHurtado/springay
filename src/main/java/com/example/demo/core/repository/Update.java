package com.example.demo.core.repository;

public interface Update<T,ID> extends Get<T,ID> {
    default void update(T entity){
        getData().remove(entity);
        getData().add(entity);
    }
}
