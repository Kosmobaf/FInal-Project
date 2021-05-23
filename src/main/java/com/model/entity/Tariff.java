package com.model.entity;

public class Tariff extends Entity {
    long idService;

    public long getIdService() {
        return idService;
    }

    public Tariff(Long id, long idService, String nameTariff, Double cost) {
        super(id);
        this.idService = idService;
        this.nameTariff = nameTariff;
        this.cost = cost;
    }

    private String nameTariff;

    private Double cost;

    public Tariff(String nameTariff, Double cost) {
        this.nameTariff = nameTariff;
        this.cost = cost;
    }

    public void setIdService(long idService) {
        this.idService = idService;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "idService=" + idService +
                ", nameTariff='" + nameTariff + '\'' +
                ", cost=" + cost +
                '}';
    }

    public String getNameTariff() {
        return nameTariff;
    }

    public void setNameTariff(String nameTariff) {
        this.nameTariff = nameTariff;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Tariff() {
    }
}
