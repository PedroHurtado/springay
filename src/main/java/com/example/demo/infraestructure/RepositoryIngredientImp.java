package com.example.demo.infraestructure;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.IngredientRepository;

@Repository
public class RepositoryIngredientImp implements IngredientRepository {

    private final RepositoryIngredientJpa repository;
    public RepositoryIngredientImp(RepositoryIngredientJpa repository){
        this.repository = repository;        
    }    
    @Override
    public JpaRepository<Ingredient,UUID> getData() {
        return repository;
    }
    @Override
    public List<IngredientJpa> query(String name, int page, int size) {        
        return repository.findByCriteria(name, PageRequest.of(page, size));
    }
   
    
}
