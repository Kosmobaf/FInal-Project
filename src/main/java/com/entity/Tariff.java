package com.entity;

import java.util.Objects;

public class Tariff {
    private int id;
    private String nameTariff;
    private Double cost;
    private String nameService;

    public Tariff(int id, String nameTariff, Double cost, String nameService) {
        this.id = id;
        this.nameTariff = nameTariff;
        this.cost = cost;
        this.nameService = nameService;
    }

    public String getNameService() {
        return nameService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return id == tariff.id &&
                nameTariff.equals(tariff.nameTariff) &&
                cost.equals(tariff.cost) &&
                nameService.equals(tariff.nameService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameTariff, cost, nameService);
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public Tariff() {
    }
    
    public Tariff(String nameTariff, Double cost) {
        this.nameTariff = nameTariff;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Tariff " +
                "id=" + id +
                ", nameTariff='" + nameTariff + '\'' +
                ", cost=" + cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
