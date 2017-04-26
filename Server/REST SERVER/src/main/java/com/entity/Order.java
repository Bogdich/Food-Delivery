package com.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Adrienne on 26.04.17.
 */
public class Order {
    private int id;
    private User user;
    private Date date;
    ArrayList<Dish> dish;

    public Order() {
    }

    public Order(int id, User user, Date date, ArrayList<Dish> dish) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.dish = dish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Dish> getDish() {
        return dish;
    }

    public void setDish(ArrayList<Dish> dish) {
        this.dish = dish;
    }
}
