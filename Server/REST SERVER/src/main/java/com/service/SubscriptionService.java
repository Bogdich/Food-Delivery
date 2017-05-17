package com.service;

import com.dao.CategoryDAO;
import com.dao.DishDAO;
import com.dao.SubscriptionDAO;
import com.dao.exception.DAOException;
import com.dao.factory.DAOFactory;
import com.entity.Category;
import com.entity.Dish;
import com.entity.Subscription;

import java.math.BigDecimal;

/**
 * Created by gusvl on 08.05.2017.
 */
public class SubscriptionService {
    private DAOFactory factory = DAOFactory.getInstance(DAOFactory.Factories.MYSQL);
    private SubscriptionDAO subscriptionDAO = factory.getSubscriptionDAO();

    public void addSubscription(int userId, int categoryId) {

        Subscription subscription = new Subscription();
        subscription.setUserId(userId);
        subscription.setCategoryId(categoryId);

        try {
            subscriptionDAO.addSub(userId, categoryId);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void deleteSubscription(int userId, int categoryId) {

        Subscription subscription = new Subscription();
        subscription.setUserId(userId);
        subscription.setCategoryId(categoryId);

        try {
            subscriptionDAO.deleteSub(userId, categoryId);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
