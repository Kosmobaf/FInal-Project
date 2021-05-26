package com.model.dao.mapper;

import com.model.Fields;
import com.model.entity.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMapper implements ObjectMapper<Service>{
    @Override
    public Service extractFromResultSet(ResultSet rs) throws SQLException {
        return new Service(
                rs.getLong(Fields.ENTITY__ID),
                rs.getString(Fields.SERVICES__NAME_SERVICE)
        );
    }
}
