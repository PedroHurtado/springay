package com.example.demo.core.repository;

public interface Add<T> extends Collection<T> {
    default void add(T entity){
        getData().add(entity);
    }
}
