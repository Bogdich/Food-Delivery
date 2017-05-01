package com.mickeyco.android.navigationdrawer.database.sqlite.category;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mickeyco.android.navigationdrawer.database.sqlite.category.CategoryDbSchema.CategoryTable;

public class CategoryBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "categoryBase.db";

    public CategoryBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + CategoryTable.NAME +
                "(" +
                CategoryDbSchema.CategoryTable.Cols.ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CategoryTable.Cols.CATEGORY_ID + " INTEGER, " +
                CategoryTable.Cols.NAME + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
