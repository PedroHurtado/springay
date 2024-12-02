package com.example.demo.controller.ingredient.commands;


import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.repository.Update;
import com.example.demo.domain.Ingredient;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@Configuration
public class IngredientUpdate {

    public record Request(String name,double cost) {
    }

    @RestController
    private class Controller{
        private final Service service;
        @SuppressWarnings("unused")
        public Controller(final Service service){
            this.service = service;
        }
        @PutMapping("ingredients/{id}")        
        ResponseEntity<?> handler(@PathVariable UUID id, @RequestBody Request request){
            service.handler(id, request);
            return ResponseEntity.noContent().build();
        }
    }
    private interface Service {
        
        void handler(UUID id, Request  request );
    }

    @Component
    private class ServiceImpl implements Service{

        private final Update<Ingredient,UUID> repository;
        @SuppressWarnings("unused")
        public ServiceImpl(final Update<Ingredient,UUID> repository){
            this.repository = repository;
        }
        @Override
        public void handler(UUID id, Request request) {
            Ingredient ingredient =  repository.get(id);
            ingredient.update(request.name(), request.cost());
            repository.update(ingredient);
        }

    }

}
