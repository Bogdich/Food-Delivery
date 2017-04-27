package com.service;


import com.dao.PoolDAO;
import com.dao.UserDAO;
import com.dao.exception.DAOException;
import com.dao.factory.DAOFactory;
import com.txtDao.OrderDao;
import com.txtDao.TimetableDao;

import java.util.ArrayList;
import java.util.List;

public class Service {
//    private OrderDao orderDao;
//    private TimetableDao timetableDao;
//
//    public Service() {
//
//        orderDao = new OrderDao();
//        timetableDao = new TimetableDao();
//    }
//
//
//
//    public List<Timetable> getTimetableWithFreePlaces() {
//        List<Timetable> all = getTimetable();
//        List<Order> allOrders = orderDao.getAll();
//        for (Timetable t : all) {
//            List orders = new ArrayList();
//            for (Order order : allOrders)
//                if (isEqualsTimetable(order.getTimetable(), t))
//                    orders.add(order);
//            for (int i = Integer.parseInt(t.getPlaces()); i >= 0; i--) {
//                if (isPlaceBusy(orders, i))
//                    t.setPlaces(String.valueOf(Integer.parseInt(t.getPlaces()) - 1));
//            }
//        }
//        return all;
//    }
//
//
//    public List<Timetable> getTimetable() {
//        return timetableDao.getAll();
//    }
//
//    public Order getOrderById(String id) {
//        List<Order> all = orderDao.getAll();
//        for (Order o : all)
//            if (o.getId().equals(id))
//                return o;
//        return null;
//    }
//
//    public String undoOrder(String id) {
//        Order order = new Order();
//        order.setId(id);
//        return orderDao.delete(order);
//    }
//
//    public String addOrder(String name, String date, String time, String count) {
//        Timetable timetable = new Timetable();
//        timetable.setName(name);
//        timetable.setDate(date);
//        timetable.setTime(time);
//        String[] places = getPlace(count, timetable);
//        Order order = new Order();
//        order.setTimetable(timetable);
//        order.setPlaces(places);
//        order.setId(generateOrderId());
//        if (isExistTimetable(timetable) && places.length > 0)
//            return orderDao.create(order);
//        return "Error";
//    }
//
//    private boolean isExistTimetable(Timetable timetable) {
//        List<Timetable> all = getTimetable();
//        for (Timetable t : all)
//            if (isEqualsTimetable(t, timetable))
//                return true;
//        return false;
//    }
//
//    private String[] getPlace(String count, Timetable timetable) {
//        int allPlaces = getAllPlaces(timetable);
//        String place[] = new String[allPlaces];
//        if (allPlaces == 0)
//            return place;
//        place = findEmptyPlace(Integer.parseInt(count), timetable, allPlaces);
//        return place;
//    }
//
//    private String[] findEmptyPlace(int count, Timetable timetable, int allPlaces) {
//        List<Order> allOrders = orderDao.getAll();
//        List<Order> ordersOnTimetable = new ArrayList<>();
//        String[] places = new String[count];
//        for (Order order : allOrders)
//            if (isEqualsTimetable(order.getTimetable(), timetable))
//                ordersOnTimetable.add(order);
//        int currentPlace = 1;
//        int len = 0;
//        while (len < count) {
//            if (currentPlace > allPlaces)
//                break;
//            if (isPlaceBusy(ordersOnTimetable, currentPlace)) {
//                currentPlace++;
//                continue;
//            }
//            places[len] = String.valueOf(currentPlace);
//            currentPlace++;
//            len++;
//
//        }
//        if (len == count)
//            return places;
//        else
//            return new String[0];
//    }
//
//    private boolean isPlaceBusy(List<Order> orders, int place) {
//        for (Order o : orders) {
//            String[] places = o.getPlaces();
//            for (String p : places)
//                if (Integer.valueOf(p) == place)
//                    return true;
//        }
//        return false;
//    }
//
//
//    private boolean isEqualsTimetable(Timetable t1, Timetable t2) {
//        if (t1.getName().equals(t2.getName()) && t1.getDate().equals(t2.getDate()) &&
//                t1.getTime().equals(t2.getTime()))
//            return true;
//        return false;
//    }
//
//    private int getAllPlaces(Timetable timetable) {
//        Timetable t = timetableDao.getTimetable(timetable.getName(), timetable.getDate(), timetable.getTime());
//        if (t != null)
//            return Integer.parseInt(t.getPlaces());
//        return 0;
//    }
//
//    private String generateOrderId() {
//        List<Order> all = orderDao.getAll();
//        if (all.size() == 0)
//            return "1";
//        int id = Integer.parseInt(all.get(all.size() - 1).getId());
//        return String.valueOf(id + 1);
//    }
//


}
