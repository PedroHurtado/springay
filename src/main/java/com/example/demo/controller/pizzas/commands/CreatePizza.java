package com.example.demo.controller.pizzas.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.repository.Add;
import com.example.demo.core.repository.Get;
import com.example.demo.domain.Ingredient;
import com.example.demo.domain.Pizza;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Configuration
public class CreatePizza {

    public record Request(
            String name,
            String description,
            String url,
            List<UUID> ingredients) {

    }

    public record ResponseIngredient(
            UUID id,
            String name) {

    }

    public record Response(
            UUID id,
            String name,
            String description,
            String url,
            double price,
            List<ResponseIngredient> ingredients

    ) {
    }

    @RestController
    public class Controller {
        private final Service service;
        public Controller(final Service service){
            this.service = service;
        }
        @PostMapping("/pizzas")
        public ResponseEntity<?> handler(@RequestBody Request request) {
            Response response = service.handler(request);              
            return ResponseEntity.status(201).body(response);
            
        }
        
    }

    public interface Service {

        Response handler(Request request);
    }

    @Component
    public class ServiceImpl implements Service {

        private final Get<Ingredient, UUID> repositoryIngredient;
        private final Add<Pizza> repository;

        public ServiceImpl(
            final Get<Ingredient, UUID> repositoryIngredient,
            final Add<Pizza> repository
        ) {
            this.repositoryIngredient = repositoryIngredient;
            this.repository = repository;
        }

        @Override
        public Response handler(Request request) {
            
            List<Ingredient> ingredients = new ArrayList<>();

            for (UUID ingredient : request.ingredients()) {
                ingredients.add(repositoryIngredient.get(ingredient));
            }

            Pizza pizza = Pizza.create(
                    request.name(),
                    request.description(),
                    request.url(),
                    ingredients);

            repository.add(pizza);
            
            return new Response(
                    pizza.getId(), 
                    pizza.getName(),
                    pizza.getDescription(),
                    pizza.getUrl(),
                    pizza.getPrice(),
                    pizza.getIngredients().stream().map(i->new ResponseIngredient(
                        i.getId(),
                        i.getName()
                    )).toList()
                );
        }

    }
}
