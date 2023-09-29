package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long questionId = rs.getLong("question_id");
        String questionTitle = rs.getString("question_title");
        Long optionAId = rs.getLong("answer_option_id");
        String optionA = rs.getString("answer_option_title");
        rs.next();
        Long optionBId = rs.getLong("answer_option_id");
        String optionB = rs.getString("answer_option_title");
        rs.next();
        Long optionCId = rs.getLong("answer_option_id");
        String optionC = rs.getString("answer_option_title");
        rs.next();
        Long optionDId = rs.getLong("answer_option_id");
        String optionD = rs.getString("answer_option_title");

        return new Question(questionId, questionTitle,
                optionAId, optionA, optionBId, optionB,
                optionCId, optionC, optionDId, optionD);
    }
}
