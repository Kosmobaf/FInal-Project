package com.model.service;

import com.controller.MyException;
import com.model.Status;
import com.model.bean.UserOrderBean;
import com.model.dao.dao_factory.DaoFactory;
import com.model.dao.dao_factory.UserDao;
import com.model.dao.ConnectionPoolHolder;
import com.model.dao.impl.JDBCTariffDao;
import com.model.dao.impl.JDBCUserDao;
import com.model.dao.impl.JDBCUserOrderDao;
import com.model.entity.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers() {
        List<User> userList;

        try (UserDao userDao = daoFactory.createUserDao()) {
            userList = userDao.findAll();
            return userList;
        }
    }


    public User getUser(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {

            return dao.findByLogin(login);
        }
    }

    public void createUser(String login, String password) throws MyException {
        try (UserDao dao = daoFactory.createUserDao()) {

            User user = new User.Builder().
                    login(login).
                    password(password).
                    build();

            dao.create(user);
        }
    }

    public boolean userIsExist(String login, String password) {

        try (UserDao dao = daoFactory.createUserDao()) {
            Optional<User> user = dao.findAll().stream().
                    filter(user1 -> user1.getLogin().equals(login)).findFirst();

            if (user.isPresent()) {
                User user1 = user.get();
                return password.equals(user1.getPassword());
            }
        }
        return false;
    }

    public String getUserLogin(long id) {
        try (UserDao dao = daoFactory.createUserDao()) {

            return dao.findById(id).getLogin();
        }
    }

    public BigDecimal getUserCash(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {

            User user = dao.findByLogin(login);
            return user.getCash();
        }
    }

    public boolean withdrawCashFromUser(String login, long idTariff) throws MyException {
        Connection connection;
        try {
            connection = ConnectionPoolHolder.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        try (JDBCUserDao jdbcUserDao = new JDBCUserDao(connection);
             JDBCTariffDao jdbcTariffDao = new JDBCTariffDao(connection);
             JDBCUserOrderDao jdbcUserOrderDao = new JDBCUserOrderDao(connection)) {

            User user = jdbcUserDao.findByLogin(login);
            long idUser = user.getId();
            UserOrderBean orderBean = jdbcUserOrderDao.findByIdTariffAndIdUser(idTariff, idUser);
            orderBean.setStatus(Status.ACTIVE.getName());

            BigDecimal coast = jdbcTariffDao.findById(idTariff).getCost();
            BigDecimal firstCash = user.getCash();
            BigDecimal lastCash = firstCash.subtract(coast);

            if (lastCash.compareTo(BigDecimal.ZERO) < 0) {
                return false;
            }
            user.setCash(lastCash);

            Objects.requireNonNull(connection).setAutoCommit(false);

            jdbcUserOrderDao.update(orderBean);
            jdbcUserDao.update(user);

            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                Objects.requireNonNull(connection).rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void addCashToUser(String login, BigDecimal incomingCash) throws MyException {
        try (UserDao userDao = daoFactory.createUserDao()) {

            User user = userDao.findByLogin(login);
            BigDecimal firstCash = user.getCash();
            BigDecimal lastCash = firstCash.add(incomingCash);

            user.setCash(lastCash);
            userDao.update(user);
        }
    }

    public void changeStatusUser(long idUser) {
        try (UserDao dao = daoFactory.createUserDao()) {
            User user = dao.findById(idUser);

            if (user.getStatus() == Status.ACTIVE) {
                user.setStatus(Status.BLOCKED.getName());
                dao.update(user);
                return;
            }
            user.setStatus(Status.ACTIVE.getName());
            dao.update(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUser(long idUser) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findById(idUser);
        }
    }
}
