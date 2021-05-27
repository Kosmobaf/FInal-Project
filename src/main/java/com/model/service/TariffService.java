package com.model.service;

import com.model.dao.DaoFactory;
import com.model.entity.Tariff;

import java.util.List;
import java.util.stream.Collectors;

public class TariffService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    public List<Tariff> getAllTariffByService(Long id) {
return daoFactory.createTariffDao().findAll().stream().
        filter(tariff -> tariff.getId_Services().equals(id)).
        collect(Collectors.toList());
    }

    public void addTariff(Long idTariff) {

    }
}
