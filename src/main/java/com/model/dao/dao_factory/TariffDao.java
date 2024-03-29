package com.model.dao.dao_factory;

import com.model.entity.Tariff;

import java.util.List;

public interface TariffDao extends GenericDao<Tariff> {

    int getNoOfRecords();

    List<Tariff> findTariffsFromPage(int offset, int noOfRecords);

    List<Tariff> findAllTariffFromOneServiceAndSorted(long idService, String sort);
}
