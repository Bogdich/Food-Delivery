package com.service;

import com.dao.CategoryDAO;
import com.dao.DishDAO;
import com.dao.exception.DAOException;
import com.dao.factory.DAOFactory;
import com.entity.Category;
import com.entity.Dish;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KirillBudevich on 29.04.17.
 */
public class DishAndCategoryService {

    DAOFactory factory = DAOFactory.getInstance(DAOFactory.Factories.MYSQL);
    DishDAO dishDAO = factory.getDishDAO();
    CategoryDAO categoryDAO = factory.getCategoryDAO();

    public int insertDish(String name, String description, int weight, BigDecimal price, int category_id) {

        Category category = new Category();
        category.setId(category_id);
        Dish dish = new Dish(0, name, description, weight, price, category);

        int id = 0;
        try {
            id = dishDAO.insert(dish);

        } catch (DAOException e) {
            e.printStackTrace();
        }

        return id;
    }

    public int insertCategory(String name) {

        Category category = new Category(0, name);

        int id = 0;
        try {
            id = categoryDAO.insert(category);

        } catch (DAOException e) {
            e.printStackTrace();
        }

        return id;
    }

    public List<Category> getAllCategories() {

        List<Category> categories = null;

        try {
            categories = categoryDAO.findAll();

        } catch (DAOException e) {
            e.printStackTrace();
        }

        return categories;

    }

    public Category getCategoryById(int categoryId) {

        Category category = null;

        try {
            category = categoryDAO.findById(categoryId);

        } catch (DAOException e) {
            e.printStackTrace();
        }

        return category;

    }

    public Dish getDish(int dishId)/* throws ServiceException*/ {

        Dish dish = null;


        try {
            dish = dishDAO.findById(dishId);
            Category category = categoryDAO.findById(dish.getCategory().getId());

            dish.setCategory(category);

        } catch (DAOException e) {
            e.printStackTrace();
        }

        return dish;
    }

    public void deleteDish(int id) /* throws ServiceException*/ {

        try {
            dishDAO.delete(id);

        } catch (DAOException e) {
//            throw new ServiceException("Service layer: cannot delete user", e);
        }
    }

    public void deleteCategory(int id) /* throws ServiceException*/ {

        try {
            categoryDAO.delete(id);

        } catch (DAOException e) {
//            throw new ServiceException("Service layer: cannot delete user", e);
        }
    }

    public List<Dish> getDishesForCategory(int categoryId) {

        List<Dish> dishes = null;
        try {
            dishes = dishDAO.findDishesForCategoryId(categoryId);
            Category category = categoryDAO.findById(categoryId);

            List<Dish> dihesWithCategory = new ArrayList<>();

            for (Dish dish : dishes) {
                dish.setCategory(category);
                dihesWithCategory.add(dish);
            }

        } catch (DAOException e) {
            e.printStackTrace();
        }
        return dishes;
    }
}
