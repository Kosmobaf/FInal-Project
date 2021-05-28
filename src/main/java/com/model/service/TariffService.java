package com.model.service;

import com.model.builder.UserOrderBeanBuilder;
import com.model.dao.DaoFactory;
import com.model.entity.Tariff;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TariffService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Tariff> getAllTariffByService(Long id) {
        return daoFactory.createTariffDao().findAll().stream().
                filter(tariff -> tariff.getId_Services().equals(id)).
                collect(Collectors.toList());
    }

    public void addTariff(Long idTariff, Long idUser) {
        UserOrderBeanBuilder builder = new UserOrderBeanBuilder();
        builder.setTariffId(idTariff);
        builder.setUserId(idUser);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        builder.setDateAdd(formater.format(date));
        daoFactory.createUserOrderDao().create(builder.getResult());
    }
}
