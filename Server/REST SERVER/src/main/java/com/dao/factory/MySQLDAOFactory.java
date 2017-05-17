package com.dao.factory;

import com.dao.*;
import com.dao.impl.*;
import com.dao.impl.UserDAOImpl;
import com.entity.Subscription;


public class MySQLDAOFactory extends DAOFactory {
    private static final MySQLDAOFactory INSTANCE = new MySQLDAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final UserInfoDAO userInfoDAO = new UserInfoDAOImpl();
    private final DishDAO dishDAO = new DishDAOImpl();
    private final PoolDAO poolDAO = new MySQLPoolDAO();
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();
    private final SubscriptionDAOImpl subscriptionDAO = new SubscriptionDAOImpl();

    //private final ReportDAO reportDAO = new MySQLReportDAO();

    private MySQLDAOFactory(){}

    public static MySQLDAOFactory getInstance(){
        return INSTANCE;
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }


    @Override
    public PoolDAO getPoolDAO() {
        return poolDAO;
    }

    @Override
    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    @Override
    public DishDAO getDishDAO() {
        return dishDAO;
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    @Override
    public SubscriptionDAO getSubscriptionDAO() {
        return subscriptionDAO;
    }
//
//    @Override
//    public CarDAO getCarDAO() {
//        return carDAO;
//    }
//
//    @Override
//    public ReportDAO getReportDAO() {
//        return reportDAO;
//    }
}
