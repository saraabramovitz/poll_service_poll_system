package com.pollServicePollSystem.model;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OptionBatchPreparedStatementSetter implements BatchPreparedStatementSetter {
    private List<Option> options;
    private Long questionId;

    public OptionBatchPreparedStatementSetter(List<Option> options, Long questionId) {
        this.options = options;
        this.questionId = questionId;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        Option option = options.get(i);
        OptionType optionType = OptionType.values()[i];
        ps.setLong(1, questionId);
        ps.setString(2, optionType.name());
        ps.setString(3, option.getOptionTitle());
    }

    @Override
    public int getBatchSize() {
        return options.size();
    }
}
