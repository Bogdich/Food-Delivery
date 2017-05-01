package com.mickeyco.android.navigationdrawer.database.sqlite.offer;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.mickeyco.android.navigationdrawer.database.sqlite.offer.OfferDbSchema.OfferTable;
import com.mickeyco.android.navigationdrawer.food_menu.sqlite.Offer;
import com.mickeyco.android.navigationdrawer.utils.Constants;

public class OfferCursorWrapper extends CursorWrapper {
    public OfferCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Offer getOffer() {
        Offer offer = new Offer();
        offer.setRowId(getInt(getColumnIndex(OfferTable.Cols.ROW_ID)));
        offer.setOfferId(getInt(getColumnIndex(OfferTable.Cols.OFFER_ID)));
        offer.setUrl(getString(getColumnIndex(OfferTable.Cols.URL)));
        offer.setName(getString(getColumnIndex(OfferTable.Cols.NAME)));
        offer.setPrice(getDouble(getColumnIndex(OfferTable.Cols.PRICE)));
        offer.setDescription(getString(getColumnIndex(OfferTable.Cols.DESCRIPTION)));
        offer.setPictureUrl(getString(getColumnIndex(OfferTable.Cols.PICTURE_URL)));
        offer.setCategoryId(getInt(getColumnIndex(OfferTable.Cols.CATEGORY_ID)));
        offer.setWeight(getString(getColumnIndex(OfferTable.Cols.WEIGHT)));
        if(!isNull(getColumnIndex(OfferTable.Cols.CALORIES)))
            offer.addParam(Constants.OfferParams.CALORIES, getString(getColumnIndex(OfferTable.Cols.CALORIES)));
        if(!isNull(getColumnIndex(OfferTable.Cols.PROTEINS)))
            offer.addParam(Constants.OfferParams.PROTEINS, getString(getColumnIndex(OfferTable.Cols.PROTEINS)));
        if(!isNull(getColumnIndex(OfferTable.Cols.CARBOHYDRATES)))
            offer.addParam(Constants.OfferParams.CARBOHYDRATES, getString(getColumnIndex(OfferTable.Cols.CARBOHYDRATES)));
        if(!isNull(getColumnIndex(OfferTable.Cols.FATS)))
            offer.addParam(Constants.OfferParams.FATS, getString(getColumnIndex(OfferTable.Cols.FATS)));
        if(!isNull(getColumnIndex(OfferTable.Cols.NUMBER)))
            offer.addParam(Constants.OfferParams.NUMBER, getString(getColumnIndex(OfferTable.Cols.NUMBER)));
        if(!isNull(getColumnIndex(OfferTable.Cols.DIAMETER)))
            offer.addParam(Constants.OfferParams.DIAMETER, getString(getColumnIndex(OfferTable.Cols.DIAMETER)));
        return offer;
    }
}