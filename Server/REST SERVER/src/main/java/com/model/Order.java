package com.model;

import java.util.Arrays;

/**
 * Created by Lenovo on 3/31/2017.
 */
public class Order {
    private String id;
    private Timetable timetable;
    private String[] places;

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }


    public String[] getPlaces() {
        return places;
    }

    public void setPlaces(String[] places) {
        this.places = places;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", timetable=" + timetable +
                ", places=" + Arrays.toString(places) +
                '}';
    }
}
