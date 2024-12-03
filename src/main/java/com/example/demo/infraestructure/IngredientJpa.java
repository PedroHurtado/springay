package com.example.demo.infraestructure;


import java.util.UUID;

//https://www.baeldung.com/spring-data-jpa-projections
public interface IngredientJpa {

    public UUID getId();
    public String getName();
    public Double getCost();
} 
