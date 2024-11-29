package com.example.demo.core.repository;

import com.example.demo.domain.EntityBase;

public interface Repository<T extends EntityBase,ID> extends Update<T,ID>, Remove<T,ID>, Add<T> {

    
} 
