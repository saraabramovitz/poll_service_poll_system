package com.pollServicePollSystem.model;

public class Option {
    private Long optionId;
    private String optionTitle;
    private OptionType optionType;

    public Option(Long optionId, String optionTitle, OptionType optionType) {
        this.optionId = optionId;
        this.optionTitle = optionTitle;
        this.optionType = optionType;
    }

    public Long getOptionId() {
        return optionId;
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
    }
}