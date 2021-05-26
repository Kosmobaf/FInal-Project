package com.model.entity;

import java.math.BigDecimal;

public class Tariff extends Entity {

    private String nameTariff;
    private Long id_Services;
    private BigDecimal cost;

    @Override
    public String toString() {
        return "Tariff{" +
                "nameTariff='" + nameTariff + '\'' +
                ", id_Services=" + id_Services +
                ", cost=" + cost +
                '}';
    }

    public String getNameTariff() {
        return nameTariff;
    }

    public void setNameTariff(String nameTariff) {
        this.nameTariff = nameTariff;
    }

    public Long getId_Services() {
        return id_Services;
    }

    public void setId_Services(Long id_Services) {
        this.id_Services = id_Services;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Tariff(Long id, String nameTariff, Long id_Services, BigDecimal cost) {
        super(id);
        this.nameTariff = nameTariff;
        this.id_Services = id_Services;
        this.cost = cost;
    }

    public Tariff() {
    }
}
