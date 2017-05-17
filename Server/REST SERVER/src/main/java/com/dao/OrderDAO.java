package com.dao;

import com.dao.exception.DAOException;
import com.entity.DishesAmount;
import com.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adrienne on 26.04.17.
 */
public interface OrderDAO {
    int insertOrder(int userId) throws DAOException;
    void insertDishWithOrder(int orderId, ArrayList<DishesAmount> dishesAmounts) throws DAOException;
}
