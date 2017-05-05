package com.mickeyco.android.fooddelivery.api.models;

/**
 * Created by gusvl on 25.11.2016.
 */
public class Data {
    private String specialization;
    private String name;
    private String email;
    private String time;

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
