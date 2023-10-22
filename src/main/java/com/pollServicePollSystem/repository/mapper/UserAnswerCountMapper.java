package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.UserAnswerCount;
import com.pollServicePollSystem.model.UserAnswerCountResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserAnswerCountMapper implements RowMapper<UserAnswerCountResponse> {
    @Override
    public UserAnswerCountResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAnswerCountResponse userAnswerCountResponse = new UserAnswerCountResponse(
                rs.getLong("user_id"),
                rs.getLong("answers_count")
        );
        return  userAnswerCountResponse;
    }
}
