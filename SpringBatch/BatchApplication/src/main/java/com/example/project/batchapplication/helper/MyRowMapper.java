package com.example.project.batchapplication.helper;

import com.example.project.common.domain.MyDataTransferObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Spring Row mapper class TODO.
 * <p>
 * The Spring RowMapper implementation uses a JDBC {@link ResultSet} to map the results.
 * </p>
 * @author mlglenn.
 */
@Component
public class MyRowMapper implements RowMapper<MyDataTransferObject> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRowMapper.class);


    /**
     * Maps a row from the SAMPLE table to @{link BatteryOrderDto}.
     *
     * @param rs @{link ResultSet}
     * @param rowNum integer
     * @return @{link BatteryOrderDto}
     * @throws SQLException
     */
    @Override
    public final MyDataTransferObject mapRow(final ResultSet rs, final int rowNum)
            throws SQLException {

        MyDataTransferObject dto = new MyDataTransferObject();

        dto.setId(rs.getLong("index"));
        dto.setProperty1(rs.getString("column1"));
        dto.setProperty2(rs.getString("column2"));
        dto.setProperty3("something");

        return dto;
    }

}
