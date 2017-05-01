package com.mickeyco.android.navigationdrawer.food_menu.realm;

import com.mickeyco.android.navigationdrawer.utils.Constants;

import java.util.HashMap;
import java.util.Map;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by gusvl on 15.11.2016.
 */
public class Offer extends RealmObject{

    @PrimaryKey
    private int offerId;

    @Required
    private String url;

    @Required
    private String name;

    private double price;

    @Required
    private String description;

    @Required
    private String pictureUrl;


    private int categoryId;

    @Required
    private String weight;

    private String calories;
    private String proteins;
    private String carbohydrates;
    private String fats;
    private String number;
    private String diameter;

    public Offer() {
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getProteins() {
        return proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public Map<String, String> getParams(){
        Map<String, String> params = new HashMap<>();
        if(proteins != null)
            params.put(Constants.OfferParams.PROTEINS, proteins);
        if(carbohydrates != null)
            params.put(Constants.OfferParams.CARBOHYDRATES, carbohydrates);
        if(fats != null)
            params.put(Constants.OfferParams.FATS, fats);
        if(calories != null)
            params.put(Constants.OfferParams.CALORIES, calories);
        if(number != null)
            params.put(Constants.OfferParams.NUMBER, number);
        if(diameter != null)
            params.put(Constants.OfferParams.DIAMETER, diameter);
        return params;
    }
}
