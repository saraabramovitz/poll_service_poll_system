package com.pollServicePollSystem.repository.mapper;
import com.pollServicePollSystem.model.UserOptionResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserOptionResponseMapper implements RowMapper<UserOptionResponse> {
    @Override
    public UserOptionResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long questionId = rs.getLong("QUESTION_ID");
        String questionTitle = rs.getString("QUESTION_TITLE");

        String optionATitle = null;
        Long optionACount = 0L;
        String optionBTitle = null;
        Long optionBCount = 0L;
        String optionCTitle = null;
        Long optionCCount = 0L;
        String optionDTitle = null;
        Long optionDCount = 0L;

        do {
            String optionType = rs.getString("OPTION_TYPE");
            if (optionType != null) {
                String optionTitle = rs.getString("OPTION_TITLE");
                Long optionCount = rs.getLong("USERS_COUNT");

                switch (optionType) {
                    case "a":
                        optionATitle = optionTitle;
                        optionACount = optionCount;
                        break;
                    case "b":
                        optionBTitle = optionTitle;
                        optionBCount = optionCount;
                        break;
                    case "c":
                        optionCTitle = optionTitle;
                        optionCCount = optionCount;
                        break;
                    case "d":
                        optionDTitle = optionTitle;
                        optionDCount = optionCount;
                        break;
                    default:
                        // Handle unknown option type (if needed)
                        break;
                }
            }
        } while (rs.next());

        return new UserOptionResponse(questionId, questionTitle, optionATitle, optionACount,
                optionBTitle, optionBCount, optionCTitle, optionCCount,
                optionDTitle, optionDCount);
    }
}

