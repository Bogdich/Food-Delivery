package com.dao.factory;
import com.dao.*;
import com.dao.impl.UserDAOImpl;


public abstract class DAOFactory {

	
	public static DAOFactory getInstance(Factories factoryName){
		switch (factoryName) {
			case MYSQL: return MySQLDAOFactory.getInstance();
			default: return null;
		}
	}

	public abstract UserDAO getUserDAO();

	public abstract PoolDAO getPoolDAO();

	public abstract UserInfoDAO getUserInfoDAO();

	public abstract DishDAO getDishDAO();

	public abstract CategoryDAO getCategoryDAO();

	public abstract SubscriptionDAO getSubscriptionDAO();

	public abstract OrderDAO getOrderDAO();
//
//	public abstract ReportDAO getReportDAO();

	public enum Factories {
		MYSQL
	}

}
