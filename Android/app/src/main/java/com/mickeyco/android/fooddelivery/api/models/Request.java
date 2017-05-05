package com.mickeyco.android.fooddelivery.api.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gusvl on 28.11.2016.
 */
public class Request implements Parcelable {

    private String name;
    private String date;
    private String time;
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    protected Request(Parcel in) {
        name = in.readString();
        date = in.readString();
        time = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };
}