package com.example.demo.infraestructure;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.demo.domain.Pizza;
import com.example.demo.domain.PizzaRepository;

@Component
public class RepositoryPizzaImpl implements PizzaRepository {

    private static Set<Pizza> pizzas = new HashSet<>();
    @Override
    public Set<Pizza> getData() {
        return pizzas;
    }
    
}
