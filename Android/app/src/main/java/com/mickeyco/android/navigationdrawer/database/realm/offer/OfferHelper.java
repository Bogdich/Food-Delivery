package com.mickeyco.android.navigationdrawer.database.realm.offer;

import android.support.annotation.NonNull;

import com.mickeyco.android.navigationdrawer.food_menu.realm.Offer;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by gusvl on 15.11.2016.
 */
public class OfferHelper {

    public static void save(@NonNull Realm realm, List<Offer> offers) {
        realm.beginTransaction();
        realm.delete(Offer.class);
        realm.copyToRealm(offers);
        realm.commitTransaction();
    }

    public static void save(@NonNull Realm realm, Offer offer) {
        realm.beginTransaction();
        realm.copyToRealm(offer);
        realm.commitTransaction();
    }

    public static List<Offer> getByCategory(@NonNull Realm realm, int categoryId){
        realm.beginTransaction();
        RealmResults<Offer> result = realm.where(Offer.class).equalTo("categoryId", categoryId)
                .findAll();
        realm.commitTransaction();
        return new ArrayList<>(result);
    }
    public static List<Offer> get(@NonNull Realm realm){
        realm.beginTransaction();
        RealmResults<Offer> result = realm.where(Offer.class)
                .findAll();
        realm.commitTransaction();
        return new ArrayList<>(result);
    }
    public static Offer get(@NonNull Realm realm, int offerId){
        realm.beginTransaction();
        Offer result = realm.where(Offer.class).equalTo("offerId", offerId)
                .findFirst();
        realm.commitTransaction();
        return result;
    }
}
