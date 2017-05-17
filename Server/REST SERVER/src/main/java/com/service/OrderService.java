package com.service;

import com.dao.OrderDAO;
import com.dao.exception.DAOException;
import com.dao.factory.DAOFactory;
import com.entity.DishesAmount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gusvl on 11.05.2017.
 */
public class OrderService {
    private DAOFactory factory = DAOFactory.getInstance(DAOFactory.Factories.MYSQL);
    private OrderDAO orderDAO = factory.getOrderDAO();

    public int insertOrder(int userId) {
        int id = 0;
        try {
            id = orderDAO.insertOrder(userId);

        } catch (DAOException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void insertDishWithOrder(int orderId, ArrayList<DishesAmount> dishesAmounts) {
        try {
            orderDAO.insertDishWithOrder(orderId, dishesAmounts);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
