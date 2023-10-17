package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.Option;
import com.pollServicePollSystem.model.OptionType;
import com.pollServicePollSystem.model.Question;
import com.pollServicePollSystem.repository.QuestionRepository;
import com.pollServicePollSystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@Component
public class QuestionMapper implements RowMapper<Question> {

    @Autowired
    QuestionService questionService;

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long questionId = rs.getLong("question_id");
        String questionTitle = rs.getString("question_title");

        ArrayList<Option> options = new ArrayList<>();
        do {
            Long optionId = rs.getLong("answer_option_id");
            String optionTitle = rs.getString("answer_option_title");
            OptionType optionType = OptionType.valueOf(rs.getString("option_type"));

            Long optionQuestionId = questionService.getQuestionIdByOptionId(optionId);
            if (optionQuestionId == questionId) {
                options.add(new Option(optionId, optionTitle, optionType));
            } else {
                break;
            }
        } while (rs.next());

        return new Question(questionId, questionTitle, options);
    }

}