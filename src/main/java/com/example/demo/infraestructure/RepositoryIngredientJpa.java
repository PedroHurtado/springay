package com.example.demo.infraestructure;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Ingredient;
 
public interface RepositoryIngredientJpa extends JpaRepository<Ingredient,UUID> {
     
    @Query("SELECT i.id as id, i.name as name, i.cost as cost FROM Ingredient i WHERE (:name is NULL OR name LIKE %:name%)")    
    List<IngredientJpa> findByCriteria(@Param("name") String name, PageRequest pageable);
}
