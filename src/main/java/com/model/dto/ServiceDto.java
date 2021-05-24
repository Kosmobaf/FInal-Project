package com.model.dto;

import com.model.entity.Tariff;

import java.util.List;

public class ServiceDto {
    long id;
    String nameService;
    List<Tariff> tariffList;

    @Override
    public String toString() {
        return "ServiceDto{" +
                "id=" + id +
                ", nameService='" + nameService + '\'' +
                ", tariffList=" + tariffList +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public List<Tariff> getTariffList() {
        return tariffList;
    }

    public void setTariffList(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }

    public ServiceDto(long id, String nameService, List<Tariff> tariffList) {
        this.id = id;
        this.nameService = nameService;
        this.tariffList = tariffList;
    }
}
