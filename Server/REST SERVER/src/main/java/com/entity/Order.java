package com.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Adrienne on 26.04.17.
 */
public class Order {
    private int id;
    private int userId;

    public Order() {
    }

    public Order(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
