package com.model.entity;

import java.math.BigDecimal;

public class Tariff extends Entity {

    private String nameTariff;
    private Long idServices;
    private BigDecimal cost;

    @Override
    public String toString() {
        return "Tariff{" +
                "nameTariff='" + nameTariff + '\'' +
                ", id_Services=" + idServices +
                ", cost=" + cost +
                '}';
    }

    public String getNameTariff() {
        return nameTariff;
    }

    public void setNameTariff(String nameTariff) {
        this.nameTariff = nameTariff;
    }

    public Long getIdServices() {
        return idServices;
    }

    public void setIdServices(Long idServices) {
        this.idServices = idServices;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Tariff(Long id, String nameTariff, Long idServices, BigDecimal cost) {
        super(id);
        this.nameTariff = nameTariff;
        this.idServices = idServices;
        this.cost = cost;
    }

    public Tariff() {
    }
}
