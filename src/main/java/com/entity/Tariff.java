package com.entity;

import java.util.Objects;

public class Tariff extends Entity {
    private String nameTariff;
    private Double cost;

    public Tariff(String nameTariff, Double cost) {
        this.nameTariff = nameTariff;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "nameTariff='" + nameTariff + '\'' +
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
