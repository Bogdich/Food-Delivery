package com.mickeyco.android.fooddelivery.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Softteco on 01.05.2017.
 */

public class RegistrationResponse {
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("responseID")
    @Expose
    private Integer userId;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
