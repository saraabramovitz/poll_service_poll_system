package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionMapper implements RowMapper<Question>{

        @Override
        public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
            Question question = new Question(
                    rs.getLong("question_id"),
                    rs.getString("question_title"),
                    rs.getString("first_answer_option"),
                    rs.getString("second_answer_option"),
                    rs.getString("third_answer_option"),
                    rs.getString("fourth_answer_option")
            );
            return question;
        }
}

