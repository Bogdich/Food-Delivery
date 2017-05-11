package com.entity;

/**
 * Created by gusvl on 11.05.2017.
 */
public class DishesAmount {
    private int dishId;
    private int amount;

    public DishesAmount() {
    }

    public DishesAmount(int dishId, int amount) {
        this.dishId = dishId;
        this.amount = amount;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
