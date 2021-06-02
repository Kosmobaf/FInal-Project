package com.model.entity;

public abstract class Entity  {

    private Long id;

    protected Entity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected Entity(Long id) {
        this.id = id;
    }
}
