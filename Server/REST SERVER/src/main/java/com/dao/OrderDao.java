package com.dao;

import com.model.Order;
import com.model.Timetable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class OrderDao {
    private static final String ORDER_FILE = "/Users/KirillBudevich/Projects/Study/University/CourseWorkRIS/REST SERVER/order.txt";

    public String delete(Order order){
        String answer = "Error";
        List<Order> all = getAll();
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(ORDER_FILE))){
            for(Order o : all){
                if(!o.getId().equals( order.getId()))
                    writer.println(makeString(o));
                if(o.getId().equals( order.getId()))
                    answer = "Success";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return answer;
    }

    public String create(Order order){
        String answer;
        List<Order> all = getAll();
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(ORDER_FILE))) {
            for (Order o : all)
                writer.println(makeString(o));
            writer.println(makeString(order));
            answer =  order.getId() ;

        }catch (Exception e){
            e.printStackTrace();
            answer = "Error";
        }

        return answer;
    }

    private String makeString(Order order){
        String place = "";
        for(int i = 0 ; i < order.getPlaces().length; i++){
            if(i != order.getPlaces().length - 1 )
                place += order.getPlaces()[i] + ",";
            else
                place += order.getPlaces()[i];
        }
        String result = order.getId() + "&" + order.getTimetable().getName() +
                "&" + order.getTimetable().getDate() + "&" + order.getTimetable().getTime() +
                "&" + place;
        return result;
    }

    public List<Order> getAll(){
        List<Order> all = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(ORDER_FILE)))){
            while(reader.ready()){
                String[] item = reader.readLine().split("&");
                Order order = new Order();
                order.setId(item[0]);
                Timetable timetable = new Timetable();
                timetable.setName(item[1]);
                timetable.setDate(item[2]);
                timetable.setTime(item[3]);
                order.setTimetable(timetable);
                order.setPlaces(item[4].split(","));
                all.add(order);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return all;
    }

}
