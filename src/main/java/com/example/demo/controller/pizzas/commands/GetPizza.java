package com.example.demo.controller.pizzas.commands;

import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.repository.Get;
import com.example.demo.domain.Pizza;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Configuration
public class GetPizza {
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
            List<ResponseIngredient> ingredients) {
    }

    @RestController
    public class Controller {
        private final Service service;
        public Controller(final Service service){
            this.service = service;
        }
        @GetMapping("/pizzas/{id}")
        public ResponseEntity<?> getMethodName(@PathVariable UUID id) {
            return ResponseEntity.ok().body(service.handler(id));
        }
        
        
    }

    public interface Service {
        Response handler(UUID id);

    }

    @Component
    public class ServiceImpl implements Service {

        private final Get<Pizza, UUID> repository;

        public ServiceImpl(final Get<Pizza, UUID> repository) {
            this.repository = repository;
        }

        @Override
        public Response handler(UUID id) {
            Pizza pizza = repository.get(id);
            return new Response(
                    pizza.getId(),
                    pizza.getName(),
                    pizza.getDescription(),
                    pizza.getUrl(),
                    pizza.getPrice(),
                    pizza.getIngredients()
                        .stream()
                        .map(i -> new ResponseIngredient(i.getId(), i.getName())).toList()
                    );
        }

    }
}
