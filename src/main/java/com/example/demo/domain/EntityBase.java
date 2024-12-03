package com.example.demo.domain;

import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityBase {

    protected EntityBase(){
        
    }
    @Id
    private UUID id;
    protected EntityBase(UUID id){
        this.id = id;
    }
    public UUID getId(){
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EntityBase e){
            return e.id.equals(this.id);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
