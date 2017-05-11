package com.dao.impl;

import com.dao.OrderDAO;
import com.dao.connectionMySQL.ConnectionPool;
import com.dao.exception.ConnectionPoolException;
import com.dao.exception.DAOException;
import com.entity.DishesAmount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adrienne on 26.04.17.
 */
public class OrderDAOImpl implements OrderDAO {

    private static final String INSERT_ORDER_QUERY = "INSERT INTO `order` " +
            "(`user_id`) VALUES (?)";

    private static final String INSERT_DISH_WITH_ORDER_QUERY = "INSERT INTO `dish_has_order` " +
            "(`dish_id`, `order_id`, `amount`) VALUES (?, ?, ?)";

    @Override
    public int insertOrder(int userId) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ORDER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, userId);

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
            rs.close();

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot insertOrder order", e);
        } finally {
            closeStatement(preparedStatement);
            if (connection != null) {
                try {
                    connectionPool.freeConnection(connection);
                } catch (SQLException | ConnectionPoolException e) {
                    // LOGGER.error("Can not free connection from connection pool");
                }
            }
        }
        return id;
    }

    @Override
    public void insertDishWithOrder(int orderId, ArrayList<DishesAmount> dishesAmounts) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_DISH_WITH_ORDER_QUERY);


            for(DishesAmount dish: dishesAmounts) {
                preparedStatement.setInt(1, dish.getDishId());
                preparedStatement.setInt(2, orderId);
                preparedStatement.setInt(3, dish.getAmount());
                preparedStatement.executeUpdate();
            }

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot insertOrder order", e);
        } finally {
            closeStatement(preparedStatement);
            if (connection != null) {
                try {
                    connectionPool.freeConnection(connection);
                } catch (SQLException | ConnectionPoolException e) {
                    // LOGGER.error("Can not free connection from connection pool");
                }
            }
        }
    }


    public void closeStatement(java.sql.Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //LOGGER.error("Can not close statement");
        }
    }
}
