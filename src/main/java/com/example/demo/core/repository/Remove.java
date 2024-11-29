package com.example.demo.core.repository;

public interface Remove<T,ID> extends Get<T,ID> {
    default void remove(T entity){
        getData().remove(entity);
    }
}
