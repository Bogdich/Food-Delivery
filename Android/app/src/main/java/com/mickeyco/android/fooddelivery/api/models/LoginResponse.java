package com.mickeyco.android.fooddelivery.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Softteco on 01.05.2017.
 */

public class LoginResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("responseID")
    @Expose
    private Integer userId;

    public String getMessage() {
        return message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
