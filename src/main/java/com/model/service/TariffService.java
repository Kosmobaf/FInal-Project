package com.model.service;

import com.model.Status;
import com.model.bean.UserOrderBean;
import com.model.dao.DaoFactory;
import com.model.dao.TariffDao;
import com.model.dao.UserDao;
import com.model.dao.UserOrderDao;
import com.model.entity.Tariff;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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

    public void addTariffToUserOrder(Long idTariff, String login) throws Exception {
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

    public List<Tariff> getAllTariff() {
        List<Tariff> tariffList = new ArrayList<>();
        try (TariffDao dao = daoFactory.createTariffDao()) {
            tariffList = dao.findAll();
            return tariffList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tariffList;
    }

    public List<Tariff> sortByName(List<Tariff> list) {
        list.sort(Comparator.comparing(Tariff::getNameTariff));
        return list;
    }

    public List<Tariff> sortByNameReverse(List<Tariff> list) {
        list.sort(Comparator.comparing(Tariff::getNameTariff).reversed());
        return list;
    }

    public List<Tariff> sortByCost(List<Tariff> list) {
        list.sort(Comparator.comparing(Tariff::getCost));
        return list;
    }

    public void addTariff(long idService, String nameTariff, BigDecimal cost) {
        try (TariffDao dao = daoFactory.createTariffDao()) {

            Tariff tariff = new Tariff(nameTariff, idService, cost);
            dao.create(tariff);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}