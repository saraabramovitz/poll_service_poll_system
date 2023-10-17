package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.repository.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserAnswerRepositoryImpl implements UserAnswerRepository {

    public static final String USER_ANSWER_TABLE_NAME = "user_answer";
    public static final String QUESTION_TABLE_NAME = "question";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    UserOptionResponseMapper userOptionResponseMapper;
    @Autowired
    UserAnswerResponseMapper userAnswerResponseMapper;
    @Autowired
    UserArrayOptionResponseMapper userArrayOptionResponseMapper;

    @Autowired
    OptionResponseTryMapper optionResponseTryMapper;



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

    @Override
    public Long getUsersAnsweredCountByQuestionId(Long questionId) {
        String sql = "SELECT COUNT(*) FROM " + USER_ANSWER_TABLE_NAME + " WHERE question_id=?";
        return jdbcTemplate.queryForObject(sql, Long.class, questionId);
    }

    public List<OptionResponseTry> getOptionCountByQuestionId(Long questionId) {
        String sql =
             "SELECT " +
                "question.question_id, " +
                "question.question_title, " +
                "answer_option.answer_option_id, " +
                "answer_option.option_type, " +
                "answer_option.answer_option_title, " +
                "COALESCE(COUNT(user_answer.user_id), 0) AS users_count " +
             "FROM " +
                QUESTION_TABLE_NAME +
             " JOIN " +
                "(SELECT * FROM answer_option WHERE question_id = ?) answer_option " +
                     "ON question.question_id = answer_option.question_id " +
             " LEFT JOIN " +
                USER_ANSWER_TABLE_NAME +
                " ON question.question_id = user_answer.question_id " +
                "AND answer_option.answer_option_id = user_answer.answer_option_id " +
             "GROUP BY " +
                 "question.question_id, question.question_title, answer_option.answer_option_id, answer_option.option_type, answer_option.answer_option_title " + "order by " +
                 "question.question_id, answer_option.answer_option_id;";

        try {
            return jdbcTemplate.query(sql, optionResponseTryMapper, questionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<OptionResponseTry> getAllQuestionOptionCount() {
        String sql =
                "SELECT " +
                        "question.question_id, " +
                        "question.question_title, " +
                        "answer_option.answer_option_id, " +
                        "answer_option.option_type, " +
                        "answer_option.answer_option_title, " +
                        "COUNT(USER_ANSWER.USER_ID) AS users_count " +
                        "FROM " +
                        QUESTION_TABLE_NAME +
                        " JOIN " +
                        "(SELECT * FROM answer_option) answer_option " +
                        "ON question.question_id = answer_option.question_id " +
                        " LEFT JOIN " +
                        USER_ANSWER_TABLE_NAME +
                        " ON question.question_id = user_answer.question_id " +
                        "AND answer_option.answer_option_id = user_answer.answer_option_id " +
                        "GROUP BY " +
                        "question.question_id, question.question_title, answer_option.answer_option_id, answer_option.option_type, answer_option.answer_option_title " + "order by " +
                        "question.question_id, answer_option.answer_option_id;";

        return jdbcTemplate.query(sql, optionResponseTryMapper);
    }

    @Override
    public Long getUserAnswerAmountByUserId(Long userId) {
        String sql = "SELECT COUNT(*) FROM " + USER_ANSWER_TABLE_NAME + " WHERE USER_ID = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, userId);
    }

    @Override
    public List<UserAnswerResponse> getAllUserAnswers(Long userId) {
        String sql = "SELECT " +
                "q.question_id AS question_id, " +
                "q.question_title AS question_title, " +
                "ao.ANSWER_OPTION_ID  AS option_id, " +
                "ao.answer_option_title AS option_title " +
                "FROM " +
                "user_answer ua " +
                "JOIN " +
                "answer_option ao ON ua.answer_option_id = ao.answer_option_id " +
                "JOIN " +
                "question q ON ua.question_id = q.question_id " +
                "WHERE " +
                "ua.user_id = ?";

        return jdbcTemplate.query(sql, userAnswerResponseMapper, userId);
    }


}
