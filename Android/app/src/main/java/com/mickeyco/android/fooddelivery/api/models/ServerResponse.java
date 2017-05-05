package com.mickeyco.android.fooddelivery.api.models;


import java.util.ArrayList;
import java.util.List;

public class ServerResponse {

    private String result;
    private String message;
    private List<Data> data = new ArrayList<>();
    private List<Request> requests = new ArrayList<>();
    private User user;

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
