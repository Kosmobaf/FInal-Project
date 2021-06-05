package com.model.dao.mapper;

import com.model.dao.TableName;
import com.model.bean.UserOrderBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOrderMapper implements ObjectMapper<UserOrderBean> {
    @Override
    public UserOrderBean extractFromResultSet(ResultSet rs) throws SQLException {
        return new UserOrderBean.Builder()
                .id(rs.getLong(TableName.ENTITY__ID))
                .userId(rs.getLong(TableName.USERS_SERVICES_TARIFF__USER_ID))
                .tariffId(rs.getLong(TableName.USERS_SERVICES_TARIFF__TARIFF_ID))
                .status(rs.getString(TableName.USERS_SERVICES_TARIFF__STATUS))
                .dateAdd(rs.getString(TableName.USERS_SERVICES_TARIFF__DATE_ADD))
                .nameTariff(rs.getString(TableName.TARIFF__NAME_TARIFF))
                .nameService(rs.getString(TableName.SERVICES__NAME_SERVICE))
                .build();
    }

}
