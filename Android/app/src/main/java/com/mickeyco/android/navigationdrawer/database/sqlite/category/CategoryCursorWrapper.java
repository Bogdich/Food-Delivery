package com.mickeyco.android.navigationdrawer.database.sqlite.category;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.mickeyco.android.navigationdrawer.food_menu.sqlite.Category;
import com.mickeyco.android.navigationdrawer.database.sqlite.category.CategoryDbSchema.*;

public class CategoryCursorWrapper extends CursorWrapper {
    public CategoryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Category getCategory() {
        int rowId = getInt(getColumnIndex(CategoryTable.Cols.ROW_ID));
        int categoryId = getInt(getColumnIndex(CategoryTable.Cols.CATEGORY_ID));
        String name = getString(getColumnIndex(CategoryTable.Cols.NAME));
        Category category = new Category();
        category.setRowId(rowId);
        category.setCategoryId(categoryId);
        category.setName(name);
        return category;
    }
}