package com.example.demo.controller.ingredient.commands;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.repository.Remove;
import com.example.demo.domain.Ingredient;

@Configuration
public class IngredientRemove {


    @RestController
    private class Controller {
    
        private final Service service;
        @SuppressWarnings("unused")
        public Controller(final Service service){
            this.service = service;
        }
        @DeleteMapping("/ingredients/{id}")
        public ResponseEntity<?> handler(@PathVariable UUID id){
            service.handler(id);
            return ResponseEntity.noContent().build();
        }
        
    }

    private interface  Service {
        void handler(UUID id);        
    }
    @Component
    private class ServiceImpl implements Service{

        private final Remove<Ingredient,UUID> respository;
        @SuppressWarnings("unused")
        public ServiceImpl(final Remove<Ingredient,UUID> respository){
            this.respository = respository;
        }
        @Override
        public void handler(UUID id) {
            Ingredient ingredient = respository.get(id);
            respository.remove(ingredient);
        }

    }
}
