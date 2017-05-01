package com.mickeyco.android.navigationdrawer.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.mickeyco.android.navigationdrawer.R;
import com.mickeyco.android.navigationdrawer.database.realm.category.CategoryHelper;
import com.mickeyco.android.navigationdrawer.database.realm.offer.OfferHelper;
import com.mickeyco.android.navigationdrawer.food_menu.realm.Category;
import com.mickeyco.android.navigationdrawer.food_menu.realm.Offer;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import io.realm.Realm;

/**
 * Created by gusvl on 15.11.2016.
 */
public class RealmMenuXmlPullParser {

    private Context mContext;

    public RealmMenuXmlPullParser(Context context){
        mContext = context;
    }

    public void parseCategories() {
        try {
            Log.d(Constants.logTag, "ParseCategories");
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            factory.setNamespaceAware(true);
//            XmlPullParser xpp = factory.newPullParser();
//            URL input = new URL(Constants.xmlUrl); //url удаленного документа
//            xpp.setInput(input.openStream(), null);
            XmlResourceParser xpp = mContext.getResources().getXml(R.xml.menu);
            int eventType = xpp.getEventType();
            Category category = null;
            Realm realm = Realm.getDefaultInstance();
            String lastTag = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    switch (xpp.getName()) {
                        case "category":
                            realm.beginTransaction();
                            category = realm.createObject(Category.class,Integer.parseInt(xpp.getAttributeValue(0)));
                            lastTag = "category";
                            //category.setCategoryId(Integer.parseInt(xpp.getAttributeValue(0)));
                            realm.commitTransaction();
                            break;
                    }

                } else if (eventType == XmlPullParser.END_TAG) {
                    switch (xpp.getName()) {
                        case "category":
                            if (category.getCategoryId() != 0 && !category.getName().isEmpty()) {
                                CategoryHelper.save(realm, category);
                            }
                            break;
                        case "categories":
                            lastTag = "categories";
                            break;
                    }

                } else if (eventType == XmlPullParser.TEXT) {
                    switch (lastTag) {
                        case "category":
                            realm.beginTransaction();
                            category.setName(xpp.getText());
                            realm.commitTransaction();
                            break;
                    }
                }
                if(lastTag.equals("categories"))
                    break;
                eventType = xpp.next();
            }
            Log.d(Constants.logTag, "EndParseCategories");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseOffers() {
        try{
            Log.d(Constants.logTag, "ParseOffers");
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            factory.setNamespaceAware(true);
//            XmlPullParser xpp = factory.newPullParser();
//            URL input = new URL(Constants.xmlUrl); //url удаленного документа
//            xpp.setInput(input.openStream(), null);
            XmlResourceParser xpp = mContext.getResources().getXml(R.xml.menu);
            Realm realm = Realm.getDefaultInstance();
            int eventType = xpp.getEventType();
            Offer offer = null;
            String paramKey = null;
            String paramValue = null;
            String lastTag = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    switch (xpp.getName()) {
                        case "offer":
                            realm.beginTransaction();
                            offer = realm.createObject(Offer.class, Integer.parseInt(xpp.getAttributeValue(0)));
                            realm.commitTransaction();
                            lastTag = "offer";
//                            offer.setOfferId(Integer.parseInt(xpp.getAttributeValue(0)));
                            break;
                        case "url":
                            lastTag = "url";
                            break;
                        case "name":
                            lastTag = "name";
                            break;
                        case "price":
                            lastTag = "price";
                            break;
                        case "description":
                            lastTag = "description";
                            break;
                        case "picture":
                            lastTag = "picture";
                            break;
                        case "categoryId":
                            lastTag = "categoryId";
                            break;
                        case "param":
                            lastTag = "param";
                            paramKey = xpp.getAttributeValue(0);
                            break;

                    }

                } else if (eventType == XmlPullParser.END_TAG) {
                    switch (xpp.getName()) {
                        case "offer":
                            if (offer.getOfferId() != 0 && !offer.getName().isEmpty()) {
                                OfferHelper.save(realm, offer);
                            }
                            break;
                        case "param":
                            if (paramKey != null && paramValue != null) {
                                realm.beginTransaction();
                                if (paramKey.equals(Constants.OfferParams.WEIGHT))
                                    offer.setWeight(paramValue);
                                else if (paramKey.equals(Constants.OfferParams.CALORIES))
                                    offer.setCalories(paramValue);
                                else if (paramKey.equals(Constants.OfferParams.CARBOHYDRATES))
                                    offer.setCarbohydrates(paramValue);
                                else if (paramKey.equals(Constants.OfferParams.DIAMETER))
                                    offer.setDiameter(paramValue);
                                else if (paramKey.equals(Constants.OfferParams.FATS))
                                    offer.setFats(paramValue);
                                else if (paramKey.equals(Constants.OfferParams.NUMBER))
                                    offer.setNumber(paramValue);
                                else if (paramKey.equals(Constants.OfferParams.PROTEINS))
                                    offer.setCalories(paramValue);
                                realm.commitTransaction();
                            }
                            break;

                    }

                } else if (eventType == XmlPullParser.TEXT) {
                    realm.beginTransaction();
                    switch (lastTag) {
                        case "url":
                            offer.setUrl(xpp.getText());
                            break;
                        case "name":
                            offer.setName(xpp.getText());
                            break;
                        case "price":
                            offer.setPrice(Double.parseDouble(xpp.getText()));
                            break;
                        case "description":
                            offer.setDescription(xpp.getText());
                            break;
                        case "picture":
                            String url = xpp.getText();
                            if(url.endsWith("\n"))
                                url = url.substring(1, url.length() - 1);
                            offer.setPictureUrl(url);
                            break;
                        case "categoryId":
                            offer.setCategoryId(Integer.parseInt(xpp.getText()));
                            break;
                        case "param":
                            paramValue = xpp.getText();
                            break;
                    }
                    realm.commitTransaction();
                }
                eventType = xpp.next();
            }
            Log.d(Constants.logTag, "EndParseOffers");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }
}
