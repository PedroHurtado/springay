package com.example.demo.controller.ingredient.commands;

import java.util.UUID;

import br.com.fluentvalidator.*;

import static br.com.fluentvalidator.predicate.LogicalPredicate.*;
import static br.com.fluentvalidator.predicate.ObjectPredicate.*;
import static br.com.fluentvalidator.predicate.ComparablePredicate.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.repository.Add;
import com.example.demo.core.validator.ValidatorCore;
import com.example.demo.domain.Ingredient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Configuration
public class IngredientCreate {
    private record Request(String name, double cost) {
    }

    private record Response(UUID id, String name, double cost) {
    }

    @RestController
    private class Controller {

        private final Service service;

        @SuppressWarnings("unused")
        public Controller(final Service service) {
            this.service = service;
        }

        @PostMapping("/ingredients")
        public ResponseEntity<?> handler(@RequestBody Request req) {
            Response res = service.handler(req);
            return ResponseEntity.status(201).body(res);
        }
    }

    private interface Service {
        Response handler(Request req);
    }

    @Component
    private class ServiceImpl implements Service {

        private final Add<Ingredient> repository;
        private final ValidatorCore<Request> validator;

        @SuppressWarnings("unused")
        public ServiceImpl(
                final Add<Ingredient> repository,
                final ValidatorCore<Request> validator) {
            this.repository = repository;
            this.validator = validator;
        }

        @Override
        public Response handler(Request req) {

            validator.validateOrThrow(req);

            Ingredient ingredient = Ingredient.create(req.name(), req.cost());
            repository.add(ingredient);
            return new Response(
                    ingredient.getId(),
                    ingredient.getName(),
                    ingredient.getCost());
        }

    }

    @Component
    private class ValidatorRequest extends AbstractValidator<Request> implements ValidatorCore<Request> {

        @Override
        public void rules() {

            ruleFor(Request::name)
                    .must(not(nullValue()))
                    .withMessage("name is required")
                    .withFieldName("name");

            ruleFor(Request::cost)
                    .must(greaterThan(0D))
                    .withMessage("el costo es requerido")
                    .withFieldName("cost");
        }

       

    }

}
