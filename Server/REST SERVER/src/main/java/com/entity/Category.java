package com.entity;

import java.util.ArrayList;

/**
 * Created by Adrienne on 26.04.17.
 */
public class Category {
    private int id;
    private String name;
    private ArrayList<Dish> dishes;

    public Category() {
    }

    public Category(int id, String name, ArrayList<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.dishes = dishes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }
}
