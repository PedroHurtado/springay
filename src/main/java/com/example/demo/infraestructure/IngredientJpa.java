package com.example.demo.infraestructure;


import java.util.UUID;

public interface IngredientJpa {

    public UUID getId();
    public String getName();
    public Double getCost();
} 
