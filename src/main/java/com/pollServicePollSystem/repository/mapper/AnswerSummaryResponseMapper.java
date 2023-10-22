package com.pollServicePollSystem.repository.mapper;

import com.pollServicePollSystem.model.AnswerSummaryResponse;
import com.pollServicePollSystem.model.OptionCount;
import com.pollServicePollSystem.model.AnswerSummary;
import com.pollServicePollSystem.model.OptionType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnswerSummaryResponseMapper implements RowMapper<AnswerSummaryResponse> {
    @Override
    public AnswerSummaryResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

        OptionCount optionCount = new OptionCount(
                rs.getLong("option_id"),
                rs.getString("option_title"),
                OptionType.valueOf(rs.getString("option_type")),
                rs.getLong("answer_count")
        );

        AnswerSummaryResponse answerSummaryResponse = new AnswerSummaryResponse(
                rs.getLong("question_id"),
                rs.getString("question_title"),
                optionCount
        );

        return answerSummaryResponse;
    }

}
