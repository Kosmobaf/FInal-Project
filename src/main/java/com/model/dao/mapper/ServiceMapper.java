package com.model.dao.mapper;

import com.model.dao.TableName;
import com.model.entity.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMapper implements ObjectMapper<Service>{
    @Override
    public Service extractFromResultSet(ResultSet rs) throws SQLException {
        return new Service(
                rs.getLong(TableName.ENTITY__ID),
                rs.getString(TableName.SERVICES__NAME_SERVICE)
        );
    }
}
