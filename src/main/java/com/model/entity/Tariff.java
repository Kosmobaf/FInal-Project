package com.model.entity;

import java.math.BigDecimal;

public class Tariff extends Entity {
    Service service;

    private String nameTariff;

    private BigDecimal cost;

    public Tariff(Long id, Service service, String nameTariff, BigDecimal cost) {
        super(id);
        this.service = service;
        this.nameTariff = nameTariff;
        this.cost = cost;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getNameTariff() {
        return nameTariff;
    }

    public void setNameTariff(String nameTariff) {
        this.nameTariff = nameTariff;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Tariff() {
    }
}
