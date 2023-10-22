package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.UserAnswer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnswerMapper implements RowMapper<UserAnswer> {

    @Override
    public UserAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAnswer userAnswer = new UserAnswer(
        rs.getLong("question_id"),
        rs.getLong("option_id"));
        return  userAnswer;

    }

}


