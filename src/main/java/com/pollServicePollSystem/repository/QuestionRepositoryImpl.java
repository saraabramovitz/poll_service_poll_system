package com.pollServicePollSystem.repository;

import com.pollServicePollSystem.model.*;
import com.pollServicePollSystem.repository.mapper.OptionMapper;
import com.pollServicePollSystem.repository.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.pollServicePollSystem.model.Option;

import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository{

    public static final String QUESTION_TABLE_NAME = "question";
    public static final String OPTION_TABLE_NAME = "option";

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    OptionMapper optionMapper;


    @Override
    public void createQuestion (Question question) {
        String sqlQuestion = "INSERT INTO " + QUESTION_TABLE_NAME + " (question_title) values (?)";
        jdbcTemplate.update(sqlQuestion, question.getQuestionTitle());
        Long lostCreatedQuestionId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        List<Option> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            OptionType optionType = OptionType.values()[i];
            String optionSql = "INSERT INTO  "+ OPTION_TABLE_NAME + " (option_title, option_type, question_id) values (?, ?, ?)";
            jdbcTemplate.update(optionSql, options.get(i).getOptionTitle() ,optionType.name(), lostCreatedQuestionId);
        }
    }

    @Override
    public void updateQuestion(Question question) {
        String sqlQuestion = "UPDATE " + QUESTION_TABLE_NAME + " SET question_title = ? WHERE question_id = ?";
        jdbcTemplate.update(sqlQuestion, question.getQuestionTitle(), question.getQuestionId());

        List<Option> options = question.getOptions();
        for (Option option : options) {
            String optionSql = "UPDATE " + OPTION_TABLE_NAME +
                    " SET option_title = ? WHERE question_id = ? AND option_id = ?";
            jdbcTemplate.update(optionSql, option.getOptionTitle(), question.getQuestionId(), option.getOptionId());
        }
    }

    @Override
    public void updateQuestionTitle(Question question) {
        String sql = "UPDATE " + QUESTION_TABLE_NAME + " SET question_title = ? WHERE question_id = ?";
        jdbcTemplate.update(sql, question.getQuestionTitle(), question.getQuestionId());
    }

    @Override
    public void updateQuestionOptions(Question question) {
        List<Option> options = question.getOptions();
        for (Option option : options) {
            String optionSql = "UPDATE " + OPTION_TABLE_NAME +
                    " SET option_title = ? WHERE question_id = ? AND option_id = ?";
            jdbcTemplate.update(optionSql, option.getOptionTitle(), question.getQuestionId(), option.getOptionId());
        }
    }

    @Override
    public void deleteQuestionById(Long questionId) {
        String sqlOptions = "DELETE FROM " + OPTION_TABLE_NAME + " WHERE question_id=?";
        jdbcTemplate.update(sqlOptions, questionId);

        String sqlQuestion = "DELETE FROM " + QUESTION_TABLE_NAME + " WHERE question_id=?";
        jdbcTemplate.update(sqlQuestion, questionId);
    }

    @Override
    public List<QuestionResponse> getQuestionById(Long questionId) {
        String sql = "SELECT * FROM " + QUESTION_TABLE_NAME +
             " JOIN " + OPTION_TABLE_NAME +
             " ON option.question_id = question.question_id" +
             " WHERE question.question_id = ?";
        try {
            return jdbcTemplate.query(sql, questionMapper, questionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<QuestionResponse> getAllQuestions() {
        String sql = "SELECT * FROM " + QUESTION_TABLE_NAME +
            " JOIN " + OPTION_TABLE_NAME +
            " ON option.question_id = question.question_id";
        try {
            return jdbcTemplate.query(sql, questionMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Long isQuestionIdExist(Long questionId) {
        String sql = "SELECT COUNT(*) FROM " + QUESTION_TABLE_NAME +
            " WHERE question.question_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Long.class, questionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }


    @Override
    public List<QuestionResponse> getQuestionByQuestionTitle(String questionTitle) {
        String sql = "SELECT * FROM " + QUESTION_TABLE_NAME +
            " JOIN " + OPTION_TABLE_NAME +
            " ON option.question_id = question.question_id " +
            " WHERE question.question_title = ?";
        try {
           return jdbcTemplate.query(sql, questionMapper, questionTitle);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public String getAnswerByQuestionId(String answer, Long questionId) {
        String sql = "SELECT " + answer + " FROM " + QUESTION_TABLE_NAME + " WHERE question_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{questionId}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Option getOptionByQuestionIdAndOptionId(Long questionId, Long optionId) {
        String sql = "SELECT * FROM " + OPTION_TABLE_NAME +
            " WHERE option_id = ? AND question_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, optionMapper, optionId, questionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public boolean isOptionExists(String optionTitle) {
        String sql = "SELECT COUNT(*) FROM " + OPTION_TABLE_NAME + " WHERE option_title = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, optionTitle);
        return count > 0;
    }

    @Override
    public Long getQuestionIdByOptionId(Long optionId) {
        String sql = "SELECT question.question_id " +
        "FROM question " +
        "JOIN option ON option.question_id = question.question_id " +
        "WHERE option.option_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Long.class, optionId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }



}
