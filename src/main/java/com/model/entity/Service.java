package com.model.entity;

public class Service extends Entity {
    private String nameService;

    public String getNameService() {
        return nameService;
    }

    @Override
    public String toString() {
        return "Service{" +
                "nameService='" + nameService + '\'' +
                '}';
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public Service(Long id, String nameService) {
        super(id);
        this.nameService = nameService;
    }

    public Service() {
    }
}
