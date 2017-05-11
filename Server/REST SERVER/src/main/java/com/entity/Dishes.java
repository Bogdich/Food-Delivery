package com.entity;

import java.util.ArrayList;

/**
 * Created by gusvl on 12.05.2017.
 */
public class Dishes {
    private ArrayList<DishesAmount> dishesAmounts;

    public Dishes() {
    }

    public ArrayList<DishesAmount> getDishesAmounts() {
        return dishesAmounts;
    }

    public void setDishesAmounts(ArrayList<DishesAmount> dishesAmounts) {
        this.dishesAmounts = dishesAmounts;
    }
}
