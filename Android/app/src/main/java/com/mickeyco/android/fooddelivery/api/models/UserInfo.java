package com.mickeyco.android.fooddelivery.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Softteco on 03.05.2017.
 */

public class UserInfo {

    @SerializedName("user")
    private User mUser;

    @SerializedName("name")
    private String mName;

    @SerializedName("surname")
    private String mSurname;

    @SerializedName("number")
    private String mNumber;

    @SerializedName("address")
    private String mAddress;

    @SerializedName("email")
    private String mEmail;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        mSurname = surname;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }
}
