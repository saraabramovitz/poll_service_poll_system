package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserAnswerResponseMapper implements RowMapper<UserAnswerResponse> {


    @Override
    public UserAnswerResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        Option option = new Option(
            rs.getLong("option_id"),
            rs.getString("option_title"),
            OptionType.valueOf(rs.getString("option_type")));

        UserAnswerResponse userAnswerResponse = new UserAnswerResponse(
            rs.getLong("question_id"),
            rs.getString("question_title"),
            option);

    return userAnswerResponse;
    }
}
