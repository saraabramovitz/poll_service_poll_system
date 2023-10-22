package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.Option;
import com.pollServicePollSystem.model.OptionType;
import com.pollServicePollSystem.model.QuestionResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionMapper implements RowMapper<QuestionResponse> {
    @Override
    public QuestionResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

        Option option = new Option(
                rs.getLong("option_id"),
                rs.getString("option_title"),
                OptionType.valueOf(rs.getString("option_type"))
                );

        QuestionResponse questionResponse = new QuestionResponse(
                rs.getLong("question_id"),
                rs.getString("question_title"),
                option
                );

        return questionResponse;
    }
}
