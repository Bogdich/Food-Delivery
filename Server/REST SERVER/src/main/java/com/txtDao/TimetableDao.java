package com.txtDao;


import com.model.Timetable;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TimetableDao {
    private final static String TIMETABLE_FILE = "/Users/KirillBudevich/Projects/Study/University/CourseWorkRIS/REST SERVER/timetable.txt";
    public List<Timetable> getAll(){
        List<Timetable> all = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(TIMETABLE_FILE)))){
            while( reader.ready()){
                String[] item = reader.readLine().split("&");
                Timetable timetable = new Timetable();
                timetable.setName(item[0]);
                timetable.setDate(item[1]);
                timetable.setTime(item[2]);
                timetable.setPlaces(item[3]);
                all.add(timetable);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return all;
    }

    public Timetable getTimetable(String name,String data,String time){
        List<Timetable> all = getAll();
        for(Timetable t : all)
            if(t.getName().equals(name) && t.getDate().equals(data) && t.getTime().equals(time))
                return t;
        return null;
    }

}
