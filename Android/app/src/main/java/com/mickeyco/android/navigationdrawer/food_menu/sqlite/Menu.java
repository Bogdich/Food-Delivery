package com.mickeyco.android.navigationdrawer.food_menu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mickeyco.android.navigationdrawer.database.sqlite.category.CategoryBaseHelper;
import com.mickeyco.android.navigationdrawer.database.sqlite.category.CategoryCursorWrapper;
import com.mickeyco.android.navigationdrawer.database.sqlite.category.CategoryDbSchema.CategoryTable;
import com.mickeyco.android.navigationdrawer.database.sqlite.offer.OfferBaseHelper;
import com.mickeyco.android.navigationdrawer.database.sqlite.offer.OfferCursorWrapper;
import com.mickeyco.android.navigationdrawer.database.sqlite.offer.OfferDbSchema.OfferTable;
import com.mickeyco.android.navigationdrawer.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Menu{
    private Context mContext;
    private SQLiteDatabase mCategoryDatabase;
    private SQLiteDatabase mOfferDatabase;

    private static Menu ourInstance;

    public static synchronized Menu getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new Menu(context);
        }
        return ourInstance;
    }

    private Menu(Context context) {
        mContext = context;
        mCategoryDatabase = new CategoryBaseHelper(mContext).getWritableDatabase();
        mOfferDatabase = new OfferBaseHelper(mContext).getWritableDatabase();

    }


    public Offer getOffer(int id){
        OfferCursorWrapper cursor = queryOffers(OfferTable.Cols.OFFER_ID + " = ?",
                new String[]{String.valueOf(id)});

        try{
            if(cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getOffer();
        }
        finally {
            cursor.close();
        }

    }

    public Offer getOfferByName(String name){
        OfferCursorWrapper cursor = queryOffers(OfferTable.Cols.NAME + " = ?",
                new String[]{name});

        try{
            if(cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getOffer();
        }
        finally {
            cursor.close();
        }

    }

    public Category getCategoryByName(String name){
        CategoryCursorWrapper cursor = queryCategories(CategoryTable.Cols.NAME + " = ?",
                new String[]{name});

        try{
            if(cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getCategory();
        }
        finally {
            cursor.close();
        }

    }

    public List<Offer> getOffersByCategory(int categoryId){
        List<Offer> offers = new ArrayList<>();
        OfferCursorWrapper cursor = queryOffers(OfferTable.Cols.CATEGORY_ID + " = ?",
                new String[]{String.valueOf(categoryId)});
        try{
            if(cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                offers.add(cursor.getOffer());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return offers;
    }

    public List<Offer> getOffers() {
        List<Offer> offers = new ArrayList<>();
        OfferCursorWrapper cursor = queryOffers(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                offers.add(cursor.getOffer());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return offers;
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        CategoryCursorWrapper cursor = queryCategories(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                categories.add(cursor.getCategory());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return categories;
    }



    public void addCategory(Category category){
        ContentValues values = getCategoryContentValues(category);
        mCategoryDatabase.insert(CategoryTable.NAME, null, values);

    }

    public void addOffer(Offer offer){
        ContentValues values = getOfferContentValues(offer);
        mOfferDatabase.insert(OfferTable.NAME, null, values);
    }

    private static ContentValues getOfferContentValues(Offer offer) {
        ContentValues values = new ContentValues();
        //values.put(OfferTable.Cols.ROW_ID, offer.getRowId());
        values.put(OfferTable.Cols.OFFER_ID, offer.getOfferId());
        values.put(OfferTable.Cols.URL, offer.getUrl());
        values.put(OfferTable.Cols.NAME, offer.getName());
        values.put(OfferTable.Cols.PRICE, offer.getPrice());
        values.put(OfferTable.Cols.DESCRIPTION, offer.getDescription());
        values.put(OfferTable.Cols.PICTURE_URL, offer.getPictureUrl());
        values.put(OfferTable.Cols.CATEGORY_ID, offer.getCategoryId());
        values.put(OfferTable.Cols.WEIGHT, offer.getWeight());

        if(offer.getParams().get(Constants.OfferParams.CALORIES) != null)
            values.put(OfferTable.Cols.CALORIES, offer.getParams().get(Constants.OfferParams.CALORIES));
        else
            values.putNull(OfferTable.Cols.CALORIES);
        if(offer.getParams().get(Constants.OfferParams.CARBOHYDRATES) != null)
            values.put(OfferTable.Cols.CARBOHYDRATES, offer.getParams().get(Constants.OfferParams.CARBOHYDRATES));
        else
            values.putNull(OfferTable.Cols.CARBOHYDRATES);
        if(offer.getParams().get(Constants.OfferParams.PROTEINS) != null)
            values.put(OfferTable.Cols.PROTEINS, offer.getParams().get(Constants.OfferParams.PROTEINS));
        else
            values.putNull(OfferTable.Cols.PROTEINS);
        if(offer.getParams().get(Constants.OfferParams.FATS) != null)
            values.put(OfferTable.Cols.FATS, offer.getParams().get(Constants.OfferParams.FATS));
        else
            values.putNull(OfferTable.Cols.FATS);
        if(offer.getParams().get(Constants.OfferParams.NUMBER) != null)
            values.put(OfferTable.Cols.NUMBER, offer.getParams().get(Constants.OfferParams.NUMBER));
        else
            values.putNull(OfferTable.Cols.NUMBER);
        if(offer.getParams().get(Constants.OfferParams.DIAMETER) != null)
            values.put(OfferTable.Cols.DIAMETER, offer.getParams().get(Constants.OfferParams.DIAMETER));
        else
            values.putNull(OfferTable.Cols.DIAMETER);
        return values;
    }

    private static ContentValues getCategoryContentValues(Category category) {
        ContentValues values = new ContentValues();
//        values.put(CategoryTable.Cols.ROW_ID, category.getRowId());
        values.put(CategoryTable.Cols.CATEGORY_ID, category.getCategoryId());
        values.put(CategoryTable.Cols.NAME, category.getName());
        return values;
    }

    private CategoryCursorWrapper queryCategories(String whereClause, String[] whereArgs) {
        Cursor cursor = mCategoryDatabase.query(CategoryTable.NAME, null,
                whereClause, whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new CategoryCursorWrapper(cursor);
    }

    private OfferCursorWrapper queryOffers(String whereClause, String[] whereArgs) {
        Cursor cursor = mOfferDatabase.query(OfferTable.NAME, null,
                whereClause, whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new OfferCursorWrapper(cursor);
    }
}
