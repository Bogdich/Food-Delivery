package com.dao.impl;

import com.dao.SubscriptionDAO;
import com.dao.connectionMySQL.ConnectionPool;
import com.dao.exception.ConnectionPoolException;
import com.dao.exception.DAOException;

import java.sql.*;

/**
 * Created by Adrienne on 26.04.17.
 */
public class SubscriptionDAOImpl implements SubscriptionDAO{
    private static final String ADD_SUBSCRIPTION_QUERY = "INSERT INTO `subscription` " +
            "(`category_id`,`user_id`) VALUES (?, ?)";

    private static final String DELETE_SUBSCRIPTION_QUERY = "DELETE FROM `subscription` " +
            "WHERE `category_id` = ? AND `user_id` = ?; ";


    @Override
    public void addSub(int userId, int categoryId) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(ADD_SUBSCRIPTION_QUERY);

            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot add subscription", e);
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

    @Override
    public void deleteSub(int userId, int categoryId) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_SUBSCRIPTION_QUERY);

            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot delete subscription", e);
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

    public void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //LOGGER.error("Can not close statement");
        }
    }
}
