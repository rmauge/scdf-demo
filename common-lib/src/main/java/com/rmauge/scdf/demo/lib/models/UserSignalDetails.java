package com.rmauge.scdf.demo.lib.models;

public class UserSignalDetails {
    private int userId;
    private int score;
    private String salesForceId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }

    public String getSalesForceId() {
        return salesForceId;
    }

    public void setSalesForceId(final String salesForceId) {
        this.salesForceId = salesForceId;
    }
}
