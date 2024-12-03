package com.example.demo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.EntityBase;


public interface Collection<T extends EntityBase, ID> {
    JpaRepository<T,ID> getData();    
} 