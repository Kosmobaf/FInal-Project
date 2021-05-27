package com.model.dao.mapper;

import com.model.constants.Fields;
import com.model.entity.Tariff;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TariffMapper implements ObjectMapper<Tariff>{
    @Override
    public Tariff extractFromResultSet(ResultSet rs) throws SQLException {
        return new Tariff(
                rs.getLong(Fields.ENTITY__ID),
                rs.getString(Fields.TARIFF__NAME_TARIFF),
                rs.getLong(Fields.TARIFF__ID_SERVICE),
                rs.getBigDecimal(Fields.TARIFF__COST)
        );
    }

}
