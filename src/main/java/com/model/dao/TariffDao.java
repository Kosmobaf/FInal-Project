package com.model.dao;

import com.model.entity.Tariff;

import java.util.List;

public interface TariffDao extends GenericDao<Tariff> {

    List<Tariff> findAllAndSorted(String sort);
}
