package com.model.dao.mapper;

import com.model.dao.TableName;
import com.model.entity.Tariff;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TariffMapper implements ObjectMapper<Tariff>{
    @Override
    public Tariff extractFromResultSet(ResultSet rs) throws SQLException {
        return new Tariff(
                rs.getLong(TableName.ENTITY__ID),
                rs.getString(TableName.TARIFF__NAME_TARIFF),
                rs.getLong(TableName.TARIFF__ID_SERVICE),
                rs.getBigDecimal(TableName.TARIFF__COST)
        );
    }
}
