package com.model.service;

import com.model.Status;
import com.model.bean.UserOrderBean;
import com.model.constants.Constants;
import com.model.dao.DaoFactory;
import com.model.dao.TariffDao;
import com.model.dao.UserDao;
import com.model.dao.UserOrderDao;
import com.model.dao.impl.ConnectionPoolHolder;
import com.model.dao.impl.JDBCTariffDao;
import com.model.dao.impl.JDBCUserOrderDao;
import com.model.entity.Tariff;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TariffService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Tariff> getAllTariffByService(Long id) {
        try (TariffDao dao = daoFactory.createTariffDao()) {

            return dao.findAll().stream().
                    filter(tariff -> tariff.getIdServices().equals(id)).
                    collect(Collectors.toList());
        }
    }

    public void addTariffToUserOrder(Long idTariff, String login) {
        try (UserDao userDao = daoFactory.createUserDao();
             UserOrderDao orderDao = daoFactory.createUserOrderDao()) {

            long idUser = userDao.findByLogin(login).getId();
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.YYYY_MM_DD_HH_MM_SS));

            if (checkTariff(idTariff, idUser)) {
                return;
            }

            UserOrderBean userOrderBean = new UserOrderBean.Builder()
                    .tariffId(idTariff)
                    .userId(idUser)
                    .dateAdd(date)
                    .status(Status.BLOCKED.getName())
                    .build();
            orderDao.create(userOrderBean);

            new UserService().withdrawCashFromUser(login, idTariff);
        }
    }

    private boolean checkTariff(long idTariff, long idUser) {
        try (UserOrderDao orderDao = daoFactory.createUserOrderDao();
             TariffDao tariffDao = daoFactory.createTariffDao()) {

            long idServices = tariffDao.findById(idTariff).getIdServices();
            List<Tariff> list = orderDao.findAllTariffByIdUser(idUser);
            return list.stream().anyMatch(tariff -> tariff.getIdServices() == idServices);
        }
    }

    public List<Tariff> getAllTariff() {
        List<Tariff> tariffList;
        try (TariffDao dao = daoFactory.createTariffDao()) {
            tariffList = dao.findAll();
            return tariffList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public List<Tariff> getTariffsFromPage(int offset, int noOfRecords) {
        try (TariffDao dao = daoFactory.createTariffDao()) {

            return dao.findTariffsFromPage(offset, noOfRecords);
        }
    }

    public List<Tariff> sortList(String sort) {
        try (TariffDao dao = daoFactory.createTariffDao()) {

            return dao.findAllAndSorted(sort);

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public void addTariff(long idService, String nameTariff, BigDecimal cost) {
        try (TariffDao dao = daoFactory.createTariffDao()) {

            Tariff tariff = new Tariff(nameTariff, idService, cost);
            dao.create(tariff);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTariff(long idTariff) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (JDBCTariffDao jdbcTariffDao = new JDBCTariffDao(connection);
             JDBCUserOrderDao jdbcUserOrderDao = new JDBCUserOrderDao(connection)) {

            Objects.requireNonNull(connection).setAutoCommit(false);

            jdbcUserOrderDao.deleteByIdTariff(idTariff);
            jdbcTariffDao.delete(idTariff);

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Objects.requireNonNull(connection).rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getNoOfRecords() {
        try (TariffDao dao = daoFactory.createTariffDao()){
            return dao.getNoOfRecords();
        }
    }
}
