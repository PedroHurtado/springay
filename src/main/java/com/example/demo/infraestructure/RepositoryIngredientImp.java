package com.example.demo.infraestructure;


import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Ingredient;
import com.example.demo.domain.IngredientRepository;

@Component
public class RepositoryIngredientImp implements IngredientRepository {

    private final static Set<Ingredient> ingredients = new HashSet<>();

    @Override
    public Set<Ingredient> getData() {
        return ingredients;
    }     
    
}
