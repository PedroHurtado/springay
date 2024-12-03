package com.example.demo.infraestructure;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Pizza;
import com.example.demo.domain.PizzaRepository;

@Repository
public class RepositoryPizzaImpl implements PizzaRepository {

    private final RepositoryPizzaJpa repository;
    public RepositoryPizzaImpl(final RepositoryPizzaJpa repository){
        this.repository = repository;
    }
    
    @Override
    public JpaRepository<Pizza,UUID> getData() {
        return repository;
    }
    
}
