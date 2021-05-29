package com.model.service;

import com.model.bean.UserOrderBean;
import com.model.dao.DaoFactory;
import com.model.dao.UserDao;
import com.model.dao.UserOrderDao;

import java.util.List;

public class UserOrderBeanService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<UserOrderBean> getOrdersForUser(String login) throws Exception {
        List<UserOrderBean> userOrderBeans;
        try (UserDao userDao = daoFactory.createUserDao();
             UserOrderDao orderDao = daoFactory.createUserOrderDao()) {

            long id = userDao.findByLogin(login).getId();
            userOrderBeans = orderDao.findAllOrdersByIdUser(id);

            return userOrderBeans;
        }
    }
    public List<UserOrderBean> getUserOrders(Long idUser) {
        try (UserOrderDao userOrderDao = daoFactory.createUserOrderDao()) {
            return userOrderDao.findAllOrdersByIdUser(idUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
