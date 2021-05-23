package com.model.entity;


public class Service extends Entity {
    private String nameService;

    @Override
    public String toString() {
        return "Services{" +
                "nameService='" + nameService + '\'' +
                '}';
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public Service(String nameService) {
        this.nameService = nameService;
    }

    public Service() {
    }

    public Service(Long id, String nameService) {
        super(id);
        this.nameService = nameService;
    }
}
