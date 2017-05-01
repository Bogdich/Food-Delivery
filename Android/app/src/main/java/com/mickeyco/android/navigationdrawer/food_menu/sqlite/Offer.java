package com.mickeyco.android.navigationdrawer.food_menu.sqlite;

import java.util.HashMap;
import java.util.Map;

public class Offer {
    private int rowId;
    private int offerId;
    private String url;
    private String name;
    private double price;
    private String description;
    private String pictureUrl;
    private int categoryId;
    private Map<String, String> params;
    private String weight;

    public Offer() {
        params = new HashMap<>();
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getRowId() {

        return rowId;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int id) {
        this.offerId = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void addParam(String key, String value) {
        params.put(key, value);
    }
}
