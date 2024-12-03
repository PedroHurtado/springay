package com.example.demo.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Pizza extends EntityBase {

    //constante
    private final double PROFIT = 1.2D;
    
    private String name;
    private String description;
    private String url;
    @ManyToMany
    private Set<Ingredient> ingredients = new HashSet<>();

    protected Pizza(){
        
    }
    //constructor protected para no poder crear instancias
    //sino es por el metodo static interno(create)
    protected Pizza(
            UUID id, 
            String name,
            String description,
            String url,
            List<Ingredient> ingredients
        ){
        super(id);        
        this.name=name;
        this.description = description;
        this.url = url;
        this.ingredients.addAll(ingredients);
    }
    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }
    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }
    public void update(String name,String description,String url){        
        this.name=name;
        this.description = description;
        this.url = url;
    }
    //static method
    public static Pizza create(String name,String description,String url,List<Ingredient> ingredients){
        Pizza pizza = new Pizza(UUID.randomUUID(), name, description, url,ingredients);        
        return pizza;
    }    
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public String getUrl(){
        return url;
    }
    public List<Ingredient> getIngredients(){
        return ingredients.stream().toList();
    }
    
    public Double getPrice(){

        //https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html


        //[{1,2},{2,4},{3,5}]
        return (ingredients.stream()
            .map(i->i.getCost())  //transformando el ingrediente a double
             //2->4->5
            .reduce(0D, (ac,c)->ac+c)) * PROFIT;
            //0+2=2->2+4=6->6+5->11*1.2
        
        
        /*     
        Double total = 0D;
        for (Ingredient ingredient : ingredients) {
            total+=ingredient.getCost();
        }
        return total * PROFIT;
        */
    }
}
