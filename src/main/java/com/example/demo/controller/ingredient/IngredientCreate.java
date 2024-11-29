package com.example.demo.controller.ingredient;


import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.repository.Add;
import com.example.demo.domain.Ingredient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Configuration
public class IngredientCreate {
    private record Request(String name, double cost) {
    }
    private record Response(UUID id,String name, double cost) {
    }

    @RestController
    private class Controller{
        
        private final Service service;
        @SuppressWarnings("unused")
        public Controller(final Service service){
            this.service = service;
        }

        @PostMapping("/ingredients")              
        public ResponseEntity<?> handler(@RequestBody Request req){
            Response res= service.handler(req);
            return ResponseEntity.status(201).body(res);
        }
    }
    private interface  Service {
        Response handler(Request req);        
    }
    @Component
    private class ServiceImpl implements Service{

        private final Add<Ingredient> repository;
        @SuppressWarnings("unused")
        public ServiceImpl(final Add<Ingredient> repository){
            this.repository = repository;
        }
        @Override
        public Response handler(Request req) {
            Ingredient ingredient = Ingredient.create(req.name(), req.cost());
            repository.add(ingredient);
            return new Response(
                ingredient.getId(), 
                ingredient.getName(), 
                ingredient.getCost()
            );
        }

    }
}
