package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.Question;
import com.pollServicePollSystem.model.UserAnswer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnswerMapper implements RowMapper<UserAnswer> {

    @Override
    public UserAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAnswer userAnswer = new UserAnswer();

        userAnswer.setQuestionId(rs.getLong("question_id"));
        userAnswer.setAnswer(rs.getString("user_answer"));
        return  userAnswer;

    }

}


