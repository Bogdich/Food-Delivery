package com.mickeyco.android.fooddelivery.utils;

import com.mickeyco.android.fooddelivery.api.models.Dish;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Softteco on 02.05.2017.
 */

public class CartDishes {
    private static CartDishes mInstance;
    private HashMap<Dish, Integer> mDishes;

    private CartDishes() {
        mDishes = new HashMap<>();
    }

    public static CartDishes getInstance() {
        if (mInstance == null) {
            mInstance = new CartDishes();
        }
        return mInstance;
    }

    public HashMap<Dish, Integer> getDishes() {
        return mDishes;
    }
}
