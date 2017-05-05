package com.mickeyco.android.fooddelivery.api.models;


public class ServerRequest {

    private String operation;
    private User user;
    private String specialization;
    private String doctor_name;
    private String doctor_email;
    private String request_date;
    private String request_time;
    private String description;

    public String getDescription() {
        return description;
    }

    public String getDoctorEmail() {
        return doctor_email;
    }

    public void setDoctorEmail(String doctor_email) {
        this.doctor_email = doctor_email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestTime() {
        return request_time;
    }

    public void setRequestTime(String request_time) {
        this.request_time = request_time;
    }

    public String getRequestDate() {
        return request_date;
    }

    public void setRequestDate(String request_date) {
        this.request_date = request_date;
    }

    public String getDoctorName() {
        return doctor_name;
    }

    public void setDoctorName(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getOperation() {
        return operation;
    }

    public User getUser() {
        return user;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
