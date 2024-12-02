package com.example.demo.controller.ingredient.commands;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.repository.Get;
import com.example.demo.domain.Ingredient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Configuration
public class IngredientGet {

    private record Response(UUID id, String name, double cost) {
    }

    @RestController
    private class Controller {
        private final Service service;
        @SuppressWarnings("unused")
        public Controller(final Service service){
            this.service = service;
        }        
        
        @GetMapping("/ingredients/{id}")
        ResponseEntity<?> handler(@PathVariable UUID id){
            Response res = this.service.handler(id);
            return ResponseEntity.ok(res);
        }
    }

    private interface Service {
        Response handler(UUID id);

    }

    @Component
    private class ServiceImpl implements Service {
        private final Get<Ingredient, UUID> repository;
        @SuppressWarnings("unused")
        public ServiceImpl(final Get<Ingredient, UUID> repository){
            this.repository = repository;
        }
        @Override
        public Response handler(UUID id) {
            Ingredient ingredient = repository.get(id);
            return new Response(
                    ingredient.getId(),
                    ingredient.getName(),
                    ingredient.getCost());
        }

    }
}
