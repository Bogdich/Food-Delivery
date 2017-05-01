package com.mickeyco.android.navigationdrawer.food_menu.sqlite;

public class Category {
    private int rowId;
    private int categoryId;
    private String name;

    public int getRowId() {
        return rowId;
    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int id) {
        this.categoryId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }
}
