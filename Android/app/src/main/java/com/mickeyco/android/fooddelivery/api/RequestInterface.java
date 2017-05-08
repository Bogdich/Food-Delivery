package com.mickeyco.android.fooddelivery.api;


import com.mickeyco.android.fooddelivery.api.models.Category;
import com.mickeyco.android.fooddelivery.api.models.Dish;
import com.mickeyco.android.fooddelivery.api.models.LoginResponse;
import com.mickeyco.android.fooddelivery.api.models.UserInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestInterface {
    @POST("food-delivery/user/insertUser")
    Call<LoginResponse> registration(
        @Query("login") String login, @Query("password") String password,
        @Query("name") String name, @Query("surname") String surname,
        @Query("address") String address, @Query("number") String number,
        @Query("email") String email);

    @POST("food-delivery/user/insertUser")
    Call<LoginResponse> login(@Query("login") String login, @Query("password") String password);

    @GET("food-delivery/user/getInfo/{userId}")
    Call<UserInfo> getUserInfo(@Path("userId") int userId);

    Call<Void> createDish(@Query("name") String name, @Query("description") String description,
                          @Query("weight") String weight, @Query("price") String price,
                          @Query("categoryId") String categoryId, @Query("imageURL") String imageURL);

    @GET("food-delivery/category/getCategories")
    Call<ArrayList<Category>> getAllCategories();

    @GET("food-delivery/dish/getDishes/{categoryId}")
    Call<ArrayList<Dish>> getDishesByCategoryId(@Path("categoryId") int categoryId);

    @GET("food-delivery/dish/getInfo/{dishId}")
    Call<Dish> getDishById(@Path("dishId") int dishId);
}