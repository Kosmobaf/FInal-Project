package com.model.service;

import com.controller.MyException;
import com.model.bean.UserOrderBean;
import com.model.dao.dao_factory.DaoFactory;
import com.model.dao.dao_factory.UserDao;
import com.model.dao.dao_factory.UserOrderDao;

import java.util.List;

public class UserOrderBeanService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<UserOrderBean> getAllOrdersForUserByLogin(String login) {
        List<UserOrderBean> userOrderBeans;

        try (UserDao userDao = daoFactory.createUserDao();
             UserOrderDao orderDao = daoFactory.createUserOrderDao()) {

            long id = userDao.findByLogin(login).getId();
            userOrderBeans = orderDao.findAllOrdersByIdUser(id);

            return userOrderBeans;
        }
    }

    public UserOrderBean getOrderForUserByLogin(String login, long idTariff) {
        try (UserOrderDao orderDao = daoFactory.createUserOrderDao();
             UserDao userDao = daoFactory.createUserDao()) {

            long idUser = userDao.findByLogin(login).getId();

            return orderDao.findByIdTariffAndIdUser(idTariff, idUser);

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public void deleteTariffForUser(long idOrder) throws MyException {
        try (UserOrderDao dao = daoFactory.createUserOrderDao()) {

            dao.delete(idOrder);
        }
    }
}
