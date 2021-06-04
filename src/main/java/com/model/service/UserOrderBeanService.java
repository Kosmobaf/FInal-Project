package com.model.service;

import com.model.Status;
import com.model.bean.UserOrderBean;
import com.model.dao.DaoFactory;
import com.model.dao.UserDao;
import com.model.dao.UserOrderDao;

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
        throw new RuntimeException();
    }

    public List<UserOrderBean> getAllOrdersForUserById(Long idUser) {
        try (UserOrderDao userOrderDao = daoFactory.createUserOrderDao()) {

            return userOrderDao.findAllOrdersByIdUser(idUser);

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
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

    public void deleteTariffForUser(long idOrder) {
        try (UserOrderDao dao = daoFactory.createUserOrderDao()) {

            dao.delete(idOrder);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
