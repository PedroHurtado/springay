package com.example.demo.controller;

import java.util.HashSet;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.customexception.NotFounException;
import com.example.demo.domain.Ingredient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class IngredientController {

    public static Set<Ingredient> ingredients = new HashSet<>(); // simula una bb.dd en memoria

    public record RequestIngredients(String name, double cost) {
    }

    public record ResponseIngredient(UUID id, String name, double cost) {

    }

    @PostMapping("/ingredients")
    ResponseEntity<ResponseIngredient> add(@RequestBody RequestIngredients req) {

        Ingredient ingredient = Ingredient.create(req.name(), req.cost());

        ingredients.add(ingredient); // GUARDAR EN UNA BB.DD

        ResponseIngredient responseBody = new ResponseIngredient(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getCost());
        return ResponseEntity.status(201).body(responseBody);

    }

    @GetMapping("/ingredients")
    public Stream<ResponseIngredient> getAll() {
        return ingredients.stream() // recupera datos de la memoria
                .map(i -> new ResponseIngredient(
                        i.getId(),
                        i.getName(),
                        i.getCost()));
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<?> get(@PathVariable UUID id) {
        Ingredient ingredient = getIgredient(id);
        return ResponseEntity.ok(
                new ResponseIngredient(
                        ingredient.getId(),
                        ingredient.getName(),
                        ingredient.getCost()));
    }

    @PutMapping("ingredients/{id}")
    ResponseEntity<?> update(@PathVariable UUID id, @RequestBody RequestIngredients requestIngredients) {
        Ingredient ingredient = getIgredient(id);
        ingredient.update(requestIngredients.name(), requestIngredients.cost());
        return ResponseEntity.noContent().build(); // 204
    }

    @DeleteMapping("ingredients/{id}")
    ResponseEntity<?> remove(@PathVariable UUID id) {
        Ingredient ingredient = getIgredient(id);     
        ingredients.remove(ingredient);
        return ResponseEntity.noContent().build(); // 204
    }

    private Ingredient getIgredient(UUID id) {
        return ingredients.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst().orElseThrow(() -> {
                    throw new NotFounException();
                });
    }
}
