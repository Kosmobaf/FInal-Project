package com.model.service;

import com.model.bean.UserOrderBean;
import com.model.dao.DaoFactory;
import com.model.dao.UserDao;
import com.model.dao.UserOrderDao;
import com.model.entity.User;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserOrderBean> getAllOrdersForUserById(Long idUser) {
        try (UserOrderDao userOrderDao = daoFactory.createUserOrderDao()) {
            return userOrderDao.findAllOrdersByIdUser(idUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public void withdrawCashAndChangeStatus(User user, UserOrderBean bean) {
        try (UserOrderDao orderBean = daoFactory.createUserOrderDao()) {
            orderBean.withdrawCashAndChangeStatus(bean, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserOrderBean getOrderForUserByLogin(String login, long idTariff) {
        try (UserOrderDao orderDao = daoFactory.createUserOrderDao();
             UserDao userDao = daoFactory.createUserDao()) {
            long idUser = userDao.findByLogin(login).getId();
            UserOrderBean orderBean = orderDao.findByIdTariff(idTariff, idUser);
            return orderBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
