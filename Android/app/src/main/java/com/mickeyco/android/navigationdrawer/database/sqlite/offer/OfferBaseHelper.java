package com.mickeyco.android.navigationdrawer.database.sqlite.offer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mickeyco.android.navigationdrawer.database.sqlite.offer.OfferDbSchema.OfferTable;


public class OfferBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "offerBase.db";

    public OfferBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + OfferTable.NAME +
                "(" +
                OfferTable.Cols.ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                OfferTable.Cols.OFFER_ID + " INTEGER, " +
                OfferTable.Cols.NAME + " TEXT, " +
                OfferTable.Cols.URL + " TEXT, " +
                OfferTable.Cols.PRICE + " REAL, " +
                OfferTable.Cols.DESCRIPTION + " TEXT, " +
                OfferTable.Cols.PICTURE_URL + " TEXT, " +
                OfferTable.Cols.CATEGORY_ID + " TEXT, " +
                OfferTable.Cols.WEIGHT + " TEXT, " +
                OfferTable.Cols.CALORIES + " REAL, " +
                OfferTable.Cols.PROTEINS + " REAL, " +
                OfferTable.Cols.CARBOHYDRATES + " REAL, " +
                OfferTable.Cols.FATS + " REAL, " +
                OfferTable.Cols.NUMBER + " TEXT, " +
                OfferTable.Cols.DIAMETER + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
