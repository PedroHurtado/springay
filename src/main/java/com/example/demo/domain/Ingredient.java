package com.example.demo.domain;

import java.util.UUID;

import jakarta.persistence.Entity;

@Entity
public class Ingredient extends EntityBase {
    
    private String name;
    private Double cost;

    protected Ingredient(){
        
    }
    protected Ingredient(UUID id,String name,Double cost){
        super(id);        
        this.name =  name;
        this.cost = cost;
    }
    public void update(String name,Double cost){
        System.out.println("Ingredient modificado");
        this.name = name;
        this.cost = cost;
    }
    public static Ingredient create(String name,Double cost){
        System.out.println("ingredient creado");
        return new Ingredient(UUID.randomUUID(), name, cost);
    }
    
    public String getName(){
        return name;
    }
    public Double getCost(){
        return cost;
    }
}
