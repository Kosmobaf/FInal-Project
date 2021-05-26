package com.model.entity;


import java.util.List;

public class Service extends Entity {
    private String nameService;
    private Tariff tariff;
    private List<Tariff> tariffList;

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public List<Tariff> getTariffList() {
        return tariffList;
    }

    public void setTariffList(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }

    public Service(Long id, String nameService, Tariff tariff, List<Tariff> tariffList) {
        super(id);
        this.nameService = nameService;
        this.tariff = tariff;
        this.tariffList = tariffList;
    }

    public Service() {
    }
}
