package com.model.service;

import com.model.bean.UserOrderBean;
import com.model.dao.DaoFactory;
import com.model.entity.Tariff;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TariffService {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Tariff> getAllTariffByService(Long id) {
        return daoFactory.createTariffDao().findAll().stream().
                filter(tariff -> tariff.getIdServices().equals(id)).
                collect(Collectors.toList());
    }

    public void addTariff(Long idTariff, Long idUser) {
        UserOrderBean userOrderBean = new UserOrderBean.Builder().
                tariffId(idTariff).
                userId(idUser).
                build();

        userOrderBean.setDateAdd((LocalDateTime.now().
                format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS))));

        daoFactory.createUserOrderDao().create(userOrderBean);
    }
}
