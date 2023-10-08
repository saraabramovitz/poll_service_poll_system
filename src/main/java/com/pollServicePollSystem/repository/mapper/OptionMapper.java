package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.Option;
import com.pollServicePollSystem.model.OptionType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OptionMapper implements RowMapper<Option> {
    @Override
    public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
        Option option = new Option(
                rs.getLong("answer_option_id"),
                rs.getString("answer_option_title"),
                OptionType.valueOf(rs.getString("option_type"))
        );
        return option;
    }
}
