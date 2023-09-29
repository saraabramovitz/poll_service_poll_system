package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.UserAnswer;
import com.pollServicePollSystem.repository.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserAnswerRepositoryImpl implements UserAnswerRepository {

    public static final String USER_ANSWER_TABLE_NAME = "user_answer";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AnswerMapper answerMapper;


    @Override
    public void saveUserPollAnswer(Long userId, UserAnswer userAnswer) {
        String sql = "INSERT INTO " + USER_ANSWER_TABLE_NAME + " (user_id, question_id, answer_option_id) values (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                userId,
                userAnswer.getQuestionId(),
                userAnswer.getAnswerId()
        );
    }


    @Override
    public void updateUserPollAnswer(Long userId, UserAnswer userAnswer) {
        String sql = "UPDATE " + USER_ANSWER_TABLE_NAME + " SET user_answer=? WHERE user_id=? AND question_id=?" ;
        jdbcTemplate.update(
                sql,
                userAnswer.getAnswerId(),
                userId,
                userAnswer.getQuestionId()
        );
    }

    @Override
    public void deletePollAnswersByUserId(Long userId) {
        String sql = "DELETE FROM " + USER_ANSWER_TABLE_NAME + " WHERE user_id=?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public void deletePollAnswersByQuestionId(Long questionId) {
        String sql = "DELETE FROM " + USER_ANSWER_TABLE_NAME + " WHERE question_id=?";
        jdbcTemplate.update(sql, questionId);
    }

    public UserAnswer getUserAnswersByUserId(Long userId, UserAnswer userAnswer){
        Long questionId = userAnswer.getQuestionId();
        String sql = "SELECT * FROM " + USER_ANSWER_TABLE_NAME + " WHERE user_id=? AND question_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, answerMapper, userId, questionId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Empty Data Warning");
            return null;
        }
    }



}
