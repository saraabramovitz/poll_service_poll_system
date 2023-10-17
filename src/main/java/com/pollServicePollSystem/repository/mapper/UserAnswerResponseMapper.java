package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.UserAnswerResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserAnswerResponseMapper implements RowMapper<UserAnswerResponse> {
    @Override
    public UserAnswerResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAnswerResponse userAnswerResponse = new UserAnswerResponse(
                rs.getLong("question_id"),
                rs.getString("question_title"),
                rs.getLong("option_id"),
                rs.getString("option_title")
        );
        return userAnswerResponse;

    }
}
