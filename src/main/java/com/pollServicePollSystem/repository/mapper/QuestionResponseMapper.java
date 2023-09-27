package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.QuestionResponse;
import com.pollServicePollSystem.model.QuestionAnswerOption;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionResponseMapper implements RowMapper<QuestionResponse> {


    @Override
    public QuestionResponse mapRow(ResultSet rs, int rowNum) throws SQLException {


        QuestionResponse questionResponse = new QuestionResponse();

            rs.getLong("question_id");
            rs.getString("question_title");

            QuestionAnswerOption.valueOf(rs.getString("first_answer_option"));
            QuestionAnswerOption.valueOf(rs.getString("second_answer_option"));
            QuestionAnswerOption.valueOf(rs.getString("third_answer_option"));
            QuestionAnswerOption.valueOf(rs.getString("fourth_answer_option"));


        return questionResponse;
    }
}
