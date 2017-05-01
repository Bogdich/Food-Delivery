package com.mickeyco.android.navigationdrawer.food_menu.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by gusvl on 15.11.2016.
 */
public class Category extends RealmObject{

    @PrimaryKey
    private int categoryId;

    @Required
    private String name;

    public Category() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
