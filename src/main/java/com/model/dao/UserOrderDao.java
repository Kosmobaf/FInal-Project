package com.model.dao;


import com.model.bean.UserOrderBean;
import com.model.entity.Service;
import com.model.entity.Tariff;

import java.util.List;

public interface UserOrderDao extends GenericDao<UserOrderBean> {
    List<UserOrderBean> findAllOrdersByIdUser(Long id);

    List<Tariff> findAllTariffByIdUser(long idUser);
}
