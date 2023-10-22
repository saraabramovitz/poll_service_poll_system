package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.AnswerCount;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnswerCountMapper implements RowMapper<AnswerCount> {
    @Override
    public AnswerCount mapRow(ResultSet rs, int rowNum) throws SQLException {
        AnswerCount answerCount = new AnswerCount(
                rs.getLong("question_id"),
                rs.getString("question_title"),
                rs.getLong("answer_count")
        );
        return answerCount;
    }
}

