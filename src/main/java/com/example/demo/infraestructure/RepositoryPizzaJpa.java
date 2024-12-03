package com.example.demo.infraestructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Pizza;

public interface RepositoryPizzaJpa extends JpaRepository<Pizza,UUID> {

    
} 
