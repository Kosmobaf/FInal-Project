package com.model.service;

import com.model.dao.DaoFactory;
import com.model.dao.UserDao;
import com.model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers() {
        try {
            try (UserDao userDao = daoFactory.createUserDao()) {
                return userDao.findAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }


    public User getUser(Long id) {
        try (UserDao dao = daoFactory.createUserDao()) {

            return dao.findById(id);
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

    public long getUserId(String login) throws Exception {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByLogin(login).getId();
        }
    }
}
