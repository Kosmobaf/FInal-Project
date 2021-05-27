package com.model.dao;


import com.model.bean.UserOrderBean;

import java.util.List;

public interface UserOrderDao extends GenericDao<UserOrderBean> {
    List<UserOrderBean> findAllUserOrdersByIdUser(Long id);
}
