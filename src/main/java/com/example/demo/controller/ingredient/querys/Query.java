package com.example.demo.controller.ingredient.querys;

import com.example.demo.core.repository.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Ingredient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@Configuration
public class Query {
    public record Response(
            UUID id,
            String name,
            double cost) {
    }

    @RestController
    public class Controller {
        public final Service service;
        public Controller(final Service service){
            this.service = service;
        }
        @GetMapping("/ingredients")      
        public ResponseEntity<?> handler(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestHeader String dni
        ){
            return ResponseEntity.ok().body(service.handler(name, page, size));
        }
    }

    public interface Service {
        List<Response> handler(String name, int page, int size);

    }

    @Component
    public class ServiceImpl implements Service {

        private final Collection<Ingredient> repository;

        public ServiceImpl(final Collection<Ingredient> repository) {
            this.repository = repository;
        }

        @Override
        public List<Response> handler(String name, int page, int size) {
            return repository
                    .getData()
                    .stream()
                    .filter( //where
                            i -> ((name == null) ||
                                    i.getName().toLowerCase().contains(name.toLowerCase())))
                    .skip((page-1) * size) //pagina
                    .limit(size) //tamaño por página
                    .map(i -> new Response( //select
                            i.getId(),
                            i.getName(),
                            i.getCost()))
                    .toList();
        }

    }
}
