package com.mickeyco.android.fooddelivery.api.models;


import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private int mId;

    @SerializedName("login")
    private String mLogin;

    @SerializedName("password")
    private int mPass;

    @SerializedName("admin")
    private boolean isAdmin;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public int getPass() {
        return mPass;
    }

    public void setPass(int pass) {
        mPass = pass;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
