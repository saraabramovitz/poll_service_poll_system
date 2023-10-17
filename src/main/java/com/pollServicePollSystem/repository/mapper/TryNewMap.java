package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.Option;
import com.pollServicePollSystem.model.OptionType;
import com.pollServicePollSystem.model.QuestionTry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TryNewMap implements RowMapper<QuestionTry> {
    @Override
    public QuestionTry mapRow(ResultSet rs, int rowNum) throws SQLException {

        Option option = new Option(
                rs.getLong("answer_option_id"),
                rs.getString("answer_option_title"),
                OptionType.valueOf(rs.getString("option_type"))
                );

        QuestionTry questionTry = new QuestionTry(
                rs.getLong("question_id"),
                rs.getString("question_title"),
                option
                );

        return questionTry;
    }
}
