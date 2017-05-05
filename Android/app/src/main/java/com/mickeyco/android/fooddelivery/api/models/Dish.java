package com.mickeyco.android.fooddelivery.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Softteco on 01.05.2017.
 */

public class Dish {
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("weight")
    private int mWeight;

    @SerializedName("price")
    private int mPrice;

    @SerializedName("category_id")
    private int mCategoryId;

    @SerializedName("image_URL")
    private String mImageUrl;

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getWeight() {
        return mWeight;
    }

    public void setWeight(int weight) {
        mWeight = weight;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }
}
