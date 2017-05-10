package com.entity;

import java.util.HashSet;

/**
 * Created by Adrienne on 26.04.17.
 */
public class Subscription {
    private int userId;
    private int categoryId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
