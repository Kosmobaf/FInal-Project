package com.model.service;

import com.model.Status;
import com.model.bean.UserOrderBean;
import com.model.dao.DaoFactory;
import com.model.dao.TariffDao;
import com.model.dao.UserDao;
import com.model.dao.UserOrderDao;
import com.model.entity.Tariff;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TariffService {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    DaoFactory daoFactory = DaoFactory.getInstance();
    UserService userService = new UserService();

    public TariffService() {
    }

    public List<Tariff> getAllTariffByService(Long id) throws Exception {
        try (TariffDao dao = daoFactory.createTariffDao()) {

            return dao.findAll().stream().
                    filter(tariff -> tariff.getIdServices().equals(id)).
                    collect(Collectors.toList());
        }
    }

    public void addTariff(Long idTariff, String login) throws Exception {
        try (UserDao userDao = daoFactory.createUserDao();
             UserOrderDao orderDao = daoFactory.createUserOrderDao()) {
            long idUser = userDao.findByLogin(login).getId();
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));

            if (checkTariff(idTariff, idUser)) {
                //TODO добавити повідомлення що по цій послузі вже є тариф
                return;
            }
            UserOrderBean userOrderBean = new UserOrderBean.Builder()
                    .tariffId(idTariff)
                    .userId(idUser)
                    .dateAdd(date)
                    .status(Status.BLOCKED.getName())
                    .build();
            if (userService.withdrawCashFromUser(login, idTariff)) {
                userOrderBean.setStatus(Status.ACTIVE.getName());
            }
            orderDao.create(userOrderBean);
        }
    }

    private boolean checkTariff(long idTariff, long idUser) throws Exception {
        try (UserOrderDao orderDao = daoFactory.createUserOrderDao();
             TariffDao tariffDao = daoFactory.createTariffDao()) {

            long idServices = tariffDao.findById(idTariff).getIdServices();
            List<Tariff> list = orderDao.findAllTariffByIdUser(idUser);
            boolean result = list.stream().anyMatch(tariff -> tariff.getIdServices() == idServices);
            return result;
        }
    }
}