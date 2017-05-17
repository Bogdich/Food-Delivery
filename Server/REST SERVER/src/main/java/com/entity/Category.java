package com.entity;

import java.util.ArrayList;

/**
 * Created by Adrienne on 26.04.17.
 */
public class Category {
    private int id;
    private String name;
    private String imageURL;
//    private ArrayList<Dish> dishes;

    public Category() {
    }

    public Category(int id, String name, String imageURL) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
    }

    public Category(int id, String name) {//, ArrayList<Dish> dishes) {
        this.id = id;
        this.name = name;
//        this.dishes = dishes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

//    public ArrayList<Dish> getDishes() {
//        return dishes;
//    }
//
//    public void setDishes(ArrayList<Dish> dishes) {
//        this.dishes = dishes;
//    }
}
