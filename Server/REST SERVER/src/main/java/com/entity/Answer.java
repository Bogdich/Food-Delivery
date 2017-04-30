package com.entity;

public class Answer {

    private String error;
    private int responseID;

    public int getResponseID() {
        return responseID;
    }

    public void setResponseID(int responseID) {
        this.responseID = responseID;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
