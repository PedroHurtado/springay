package com.example.demo.core.repository;


import java.util.Set;

import com.example.demo.domain.EntityBase;


public interface Collection<T extends EntityBase> {
    Set<T> getData();    
} 