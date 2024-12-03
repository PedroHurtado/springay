package com.example.demo.domain;

import java.util.List;
import java.util.UUID;

import com.example.demo.core.repository.Repository;
import com.example.demo.infraestructure.IngredientJpa;

public interface IngredientRepository extends Repository<Ingredient,UUID> {
    List<IngredientJpa> query(String name,int page, int size);
} 
