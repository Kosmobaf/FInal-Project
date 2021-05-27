package com.model.dao.mapper;

import com.model.constants.Fields;
import com.model.bean.UserOrderBean;
import com.model.builder.UserOrderBeanBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOrderMapper implements ObjectMapper<UserOrderBean>{
    @Override
    public UserOrderBean extractFromResultSet(ResultSet rs) throws SQLException {
        UserOrderBeanBuilder builder = new UserOrderBeanBuilder();
        builder.setId(rs.getLong(Fields.ENTITY__ID));
        builder.setUserId(rs.getLong(Fields.USERS_SERVICES_TARIFF__USER_ID));
        builder.setTariffId(rs.getLong(Fields.USERS_SERVICES_TARIFF__TARIFF_ID));
        builder.setActive(rs.getBoolean(Fields.USERS_SERVICES_TARIFF__IS_ACTIVE));
        builder.setDateAdd(rs.getString(Fields.USERS_SERVICES_TARIFF__DATE_ADD));
        builder.setNameTariff(rs.getString(Fields.TARIFF__NAME_TARIFF));
        builder.setNameService(rs.getString(Fields.SERVICES__NAME_SERVICE));
        return builder.getResult();
    }
}
