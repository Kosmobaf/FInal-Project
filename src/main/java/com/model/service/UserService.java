package com.model.service;

import com.model.Status;
import com.model.dao.DaoFactory;
import com.model.dao.TariffDao;
import com.model.dao.UserDao;
import com.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers() {
        List<User> userList;
        try {
            try (UserDao userDao = daoFactory.createUserDao()) {
                userList = userDao.findAll();
                return userList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }


    public User getUser(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {

            return dao.findByLogin(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();

    }

    public void createUser(String login, String password) {
        try (UserDao dao = daoFactory.createUserDao()) {

            User user = new User.Builder().
                    login(login).
                    password(password).
                    build();

            dao.create(user);
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUserLogin(long id) {
        try (UserDao dao = daoFactory.createUserDao()) {

            return dao.findById(id).getLogin();

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public BigDecimal getUserCash(String login) {
        try (UserDao dao = daoFactory.createUserDao()) {

            User user = dao.findByLogin(login);

            return user.getCash();

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public boolean withdrawCashFromUser(String login, long idTariff) {
        try (UserDao userDao = daoFactory.createUserDao();
             TariffDao tariffDao = daoFactory.createTariffDao()) {

            BigDecimal coast = tariffDao.findById(idTariff).getCost();
            User user = userDao.findByLogin(login);
            BigDecimal firstCash = user.getCash();
            BigDecimal lastCash = firstCash.subtract(coast);

            if (lastCash.compareTo(BigDecimal.ZERO) >= 0) {
                user.setCash(lastCash);
                userDao.update(user);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addCashFromUser(String login, BigDecimal incomingCash) {
        try (UserDao userDao = daoFactory.createUserDao()) {

            if (incomingCash.compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException();
            }

            User user = userDao.findByLogin(login);
            BigDecimal firstCash = user.getCash();
            BigDecimal lastCash = firstCash.add(incomingCash);

            user.setCash(lastCash);
            userDao.update(user);

        } catch (Exception e) {
            e.printStackTrace();
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
}
