package com.mickeyco.android.navigationdrawer.database.realm.category;

import android.support.annotation.NonNull;

import com.mickeyco.android.navigationdrawer.food_menu.realm.Category;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by gusvl on 15.11.2016.
 */
public class CategoryHelper {

    public static void save(@NonNull Realm realm, List<Category> categories) {
        realm.beginTransaction();
        realm.delete(Category.class);
        realm.copyToRealm(categories);
        realm.commitTransaction();
    }

    public static void save(@NonNull Realm realm,Category category) {
        realm.beginTransaction();
        realm.copyToRealm(category);
        realm.commitTransaction();
    }

    public static List<Category> get(@NonNull Realm realm,int categoryId){
        realm.beginTransaction();
        RealmResults<Category> result = realm.where(Category.class).equalTo("categoryId", categoryId)
                .findAll();
        realm.commitTransaction();
        return new ArrayList<>(result);
    }

    public static List<Category> get(@NonNull Realm realm){
        realm.beginTransaction();
        RealmResults<Category> result = realm.where(Category.class)
                .findAll();
        realm.commitTransaction();
        return new ArrayList<>(result);
    }

    public static Category getByName(@NonNull Realm realm, String name){
        realm.beginTransaction();
        Category result = realm.where(Category.class).equalTo("name", name)
                .findFirst();
        realm.commitTransaction();
        return result;
    }
}
