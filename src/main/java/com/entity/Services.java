package com.entity;

import java.util.List;
import java.util.Objects;

public class Services {
    private int id;
    private String nameService;
    private Tariff tariff;
    private List<Tariff> tariffList;

    public Services(String nameService, List<Tariff> tariffList) {
        this.nameService = nameService;
        this.tariffList = tariffList;
    }

    public Services(String nameService) {
        this.nameService = nameService;
    }

    public List<Tariff> getTariffList() {
        return tariffList;
    }

    public void setTariffList(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }

    @Override
    public String toString() {
        return "Services " +
                "id=" + id +
                ", nameService='" + nameService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Services services = (Services) o;
        return id == services.id &&
                nameService.equals(services.nameService) &&
                tariff.equals(services.tariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameService, tariff);
    }

    public Services() {
    }

    public Services(String nameService, Tariff tariff) {
        this.nameService = nameService;
        this.tariff = tariff;
    }

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
}
