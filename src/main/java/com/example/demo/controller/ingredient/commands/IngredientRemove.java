package com.example.demo.controller.ingredient.commands;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.repository.Remove;
import com.example.demo.domain.Ingredient;

@Configuration
public class IngredientRemove {

    private interface Service {
        void handler(UUID id);
    }

    @RestController
    private class Controller {

        private final Service service;      
        @SuppressWarnings("unused")
        public Controller(Remove<Ingredient, UUID> respository) {
            this.service = id -> {
                Ingredient ingredient = respository.get(id);
                respository.remove(ingredient);
            };
        }

        @DeleteMapping("/ingredients/{id}")
        public ResponseEntity<?> handler(@PathVariable UUID id) {
            service.handler(id);
            return ResponseEntity.noContent().build();
        }

    }   
}
