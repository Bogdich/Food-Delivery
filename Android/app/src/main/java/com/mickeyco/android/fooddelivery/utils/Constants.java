package com.mickeyco.android.fooddelivery.utils;

public class Constants {
    public static final String BASE_URL = "http://192.168.6.106:8050/";
    public static final String xmlUrl = "http://ufa.farfor.ru/getyml/?key=ukAXxeJYZN";
    public static final String logTag = "FoodService";

    public static final String REGISTER_OPERATION = "/user/insertUser";
    public static final String LOGIN_OPERATION = "login";
    public static final String CHANGE_PASSWORD_OPERATION = "chgPass";
    public static final String GET_SPECIALIZATIONS_OPERATION = "getSpecialz";
    public static final String GET_DOCTORS_OPERATION = "getDoctors";
    public static final String GET_TIME_OPERATION = "getTime";
    public static final String SEND_REQUEST_OPERATION = "sendRequest";
    public static final String GET_REQUESTS_OPERATION = "getRequests";
    public static final String GET_USER_REQUESTS_OPERATION = "getUserRequests";


    public static final String CATEGORY_IMAGE_URL  = "https://home-pizza.com/media/_versions_/%D0%B4%D0%B0%D1%80%D1%8B_%D0%BC%D0%BE%D1%80%D1%8F_catalog_product_detail.jpg";
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String IS_LOGGED_IN = "isLoggedIn";

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String IS_DOCTOR = "is_doctor";

    public static final String TAG = "Clinic";
    public static final String PREFS_NAME = "MyPrefsFile";

    public static final String PREF_ID = "id";

    public static final class OfferParams {
        public static final String WEIGHT = "Вес";
        public static final String CALORIES = "Каллорийность";
        public static final String PROTEINS = "Белки";
        public static final String CARBOHYDRATES = "Углеводы";
        public static final String FATS = "Жиры";
        public static final String NUMBER = "Кол-во";
        public static final String DIAMETER = "Диаметр";
    }
}
