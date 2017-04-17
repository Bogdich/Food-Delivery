package com.dao.factory;

import com.dao.*;
import com.dao.impl.*;


public class MySQLDAOFactory extends DAOFactory {
    private static final MySQLDAOFactory INSTANCE = new MySQLDAOFactory();

    private final UserDAO userDAO = new MySQLUserDAO();
    //private final CardDAO cardDAO = new MySQLCardDAO();
    //private final PoolDAO poolDAO = new MySQLPoolDAO();
    //private final CarDAO carDAO = new MySQLCarDAO();
    //private final ReportDAO reportDAO = new MySQLReportDAO();

    private MySQLDAOFactory(){}

    public static MySQLDAOFactory getInstance(){
        return INSTANCE;
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }


//    @Override
//    public PoolDAO getPoolDAO() {
//        return poolDAO;
//    }
//
//    @Override
//    public CardDAO getCardDAO() {
//        return cardDAO;
//    }
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
