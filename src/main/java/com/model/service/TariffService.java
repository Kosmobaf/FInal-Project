package com.model.service;

import com.controller.MyException;
import com.model.Status;
import com.model.bean.UserOrderBean;
import com.model.dao.dao_factory.DaoFactory;
import com.model.dao.dao_factory.TariffDao;
import com.model.dao.dao_factory.UserDao;
import com.model.dao.dao_factory.UserOrderDao;
import com.model.dao.ConnectionPoolHolder;
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

public class TariffService {
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    DaoFactory daoFactory = DaoFactory.getInstance();


    public List<Tariff> getAllTariffByServiceAndSort(Long idService, String sortCommand) {
        try (TariffDao dao = daoFactory.createTariffDao()) {

            return dao.findAllTariffFromOneServiceAndSorted(idService, sortCommand);
        }
    }

    public void addTariffToUserOrder(Long idTariff, String login) throws MyException {
        try (UserDao userDao = daoFactory.createUserDao();
             UserOrderDao orderDao = daoFactory.createUserOrderDao()) {

            long idUser = userDao.findByLogin(login).getId();
            String date = LocalDateTime.now().
                    format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));

            if (checkOnTariffForServiceAlreadyOrdered(idTariff, idUser)) {
                throw new MyException("Tariff for this service has already been ordered");
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

    private boolean checkOnTariffForServiceAlreadyOrdered(long idTariff, long idUser) {
        try (UserOrderDao orderDao = daoFactory.createUserOrderDao();
             TariffDao tariffDao = daoFactory.createTariffDao()) {

            long idServices = tariffDao.findById(idTariff).getIdServices();
            List<Tariff> list = orderDao.findAllTariffByIdUser(idUser);

            return list.stream().anyMatch(tariff -> tariff.getIdServices() == idServices);
        }
    }

    public List<Tariff> getTariffsFromPage(int offset, int noOfRecords) {
        try (TariffDao dao = daoFactory.createTariffDao()) {

            return dao.findTariffsFromPage(offset, noOfRecords);
        }
    }

    public void addTariff(long idService, String nameTariff, BigDecimal cost) throws MyException {
        try (TariffDao dao = daoFactory.createTariffDao()) {

            Tariff tariff = new Tariff(nameTariff, idService, cost);
            dao.create(tariff);
        }
    }

    public void deleteTariff(long idTariff) throws MyException {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        try (JDBCTariffDao jdbcTariffDao = new JDBCTariffDao(connection);
             JDBCUserOrderDao jdbcUserOrderDao = new JDBCUserOrderDao(connection)) {

            Objects.requireNonNull(connection).setAutoCommit(false);

            jdbcUserOrderDao.deleteByIdTariff(idTariff);
            jdbcTariffDao.delete(idTariff);

            connection.commit();
        } catch ( SQLException e) {
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
        try (TariffDao dao = daoFactory.createTariffDao()) {
            return dao.getNoOfRecords();
        }
    }
}
