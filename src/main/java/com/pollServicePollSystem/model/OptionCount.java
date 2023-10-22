package com.pollServicePollSystem.model;

public class OptionCount {
    private Long optionId;
    private String optionTitle;
    private OptionType optionType;
    private Long optionCount;

    public OptionCount(){}

    public OptionCount(Long optionId, String optionTitle, OptionType optionType, Long optionCount) {
        this.optionId = optionId;
        this.optionTitle = optionTitle;
        this.optionType = optionType;
        this.optionCount = optionCount;
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

    public Long getOptionCount() {
        return optionCount;
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

    public void setOptionCount(Long optionCount) {
        this.optionCount = optionCount;
    }

}
