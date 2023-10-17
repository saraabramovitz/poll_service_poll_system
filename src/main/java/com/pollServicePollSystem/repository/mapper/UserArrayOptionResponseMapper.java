package com.pollServicePollSystem.repository.mapper;


import com.pollServicePollSystem.model.UserOptionResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserArrayOptionResponseMapper implements RowMapper<List<UserOptionResponse>> {

    @Override
    public List<UserOptionResponse> mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<Long, UserOptionResponse> resultMap = new HashMap<>();

        while (rs.next()) {
            Long questionId = rs.getLong("question_id");
            String questionTitle = rs.getString("question_title");
            String optionType = rs.getString("option_type");
            String optionTitle = rs.getString("option_title");
            Long optionCount = rs.getLong("users_count");

            UserOptionResponse userOptionResponse = resultMap.getOrDefault(questionId, new UserOptionResponse());
            userOptionResponse.setQuestionId(questionId);
            userOptionResponse.setQuestionTitle(questionTitle);

            switch (optionType) {
                case "a":
                    userOptionResponse.setOptionATitle(optionTitle);
                    userOptionResponse.setOptionACount(optionCount);
                    break;
                case "b":
                    userOptionResponse.setOptionBTitle(optionTitle);
                    userOptionResponse.setOptionBCount(optionCount);
                    break;
                case "c":
                    userOptionResponse.setOptionCTitle(optionTitle);
                    userOptionResponse.setOptionCCount(optionCount);
                    break;
                case "d":
                    userOptionResponse.setOptionDTitle(optionTitle);
                    userOptionResponse.setOptionDCount(optionCount);
                    break;
            }

            resultMap.put(questionId, userOptionResponse);
        }

        return new ArrayList<>(resultMap.values());
    }
}
