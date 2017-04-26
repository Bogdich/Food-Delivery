package com.entity;

/**
 * Created by Adrienne on 26.04.17.
 */
public class UserInfo {
    private User user;
    private String name;
    private String surname;
    private String number;
    private String address;
    private String email;

    public UserInfo() {
    }

    public UserInfo(User user, String name, String surname, String number, String address, String email) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.address = address;
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
