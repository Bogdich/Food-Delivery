package com.dao;

import com.dao.exception.DAOException;
import com.entity.Dish;

import java.util.List;

/**
 * Created by Adrienne on 26.04.17.
 */
public interface DishDAO {

    int insert(Dish dish) throws DAOException;

    void delete(int id) throws DAOException;

    Dish findById(int id) throws DAOException;

    List<Dish> findDishesForCategoryId(int categoryId) throws DAOException;
}
