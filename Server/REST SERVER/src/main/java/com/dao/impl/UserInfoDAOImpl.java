package com.dao.impl;

import com.dao.UserInfoDAO;
import com.dao.connectionMySQL.ConnectionPool;
import com.dao.exception.ConnectionPoolException;
import com.dao.exception.DAOException;
import com.entity.User;
import com.entity.UserInfo;
import com.mysql.jdbc.log.Log;

import java.sql.*;

/**
 * Created by Adrienne on 26.04.17.
 */
public class UserInfoDAOImpl implements UserInfoDAO{

    private static final String INSERT_USER_INFO_QUERY = "INSERT INTO `user_info` " +
            "(`name`, `surname`, `address`, `email`, `number`, `user_id`) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

//    private static final String UPDATE_USER_INFO_QUERY = "UPDATE `users` " +
//            "SET `name` = ?, `surname` = ?, `address` = ?, `email` = ?, `number` = ? " +
//            "WHERE `user_id` = ?";

    private static final String UPDATE_USER_INFO_QUERY = "UPDATE user " +
            "INNER JOIN user_info ON user.id = user_info.user_id " +
            "SET user.login = ?, user.password = ?, user_info.name = ?, user_info.surname = ?, " +
            "user_info.address = ?, user_info.email = ?, user_info.number = ? " +
            "WHERE user.id = ?";

    private static final String SELECT_USER_INFO_BY_USER_ID_QUERY = "SELECT * FROM `user_info` WHERE `user_id` = ? ";

    private static final String DELETE_USER_INFO_BY_USER_ID_QUERY = "DELETE FROM `user_info` WHERE `user_id` = ? ";

    @Override
    public void insert(UserInfo info) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER_INFO_QUERY);

            preparedStatement.setString(1, info.getName());
            preparedStatement.setString(2, info.getSurname());
            preparedStatement.setString(3, info.getAddress());
            preparedStatement.setString(4, info.getEmail());
            preparedStatement.setString(5, info.getNumber());
            preparedStatement.setInt(6, info.getUser().getId());

            preparedStatement.executeUpdate();

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot insertOrder user", e);
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
    public void updateUserInfo(UserInfo userInfo) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER_INFO_QUERY);

            preparedStatement.setString(1, userInfo.getUser().getLogin());
            preparedStatement.setString(2, userInfo.getUser().getPassword());
            preparedStatement.setString(3, userInfo.getName());
            preparedStatement.setString(4, userInfo.getSurname());
            preparedStatement.setString(5, userInfo.getAddress());
            preparedStatement.setString(6, userInfo.getEmail());
            preparedStatement.setString(7, userInfo.getNumber());
            preparedStatement.setInt(8, userInfo.getUser().getId());


            preparedStatement.executeUpdate();
        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot updateUserInfo user", e);
        } finally {
            closeStatement(preparedStatement);
            if (connection != null) {
                try {
                    connectionPool.freeConnection(connection);
                } catch (SQLException | ConnectionPoolException e) {
                    //LOGGER.error("Can not free connection from connection pool");
                }
            }
        }

    }

    @Override
    public void deleteByUserId(int userId) throws DAOException {

    }

    @Override
    public UserInfo findByUserId(int userId) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSetClient = null;

        UserInfo userInfo = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SELECT_USER_INFO_BY_USER_ID_QUERY);

            preparedStatement.setInt(1, userId);
            resultSetClient = preparedStatement.executeQuery();

            if (resultSetClient.next()) {
                userInfo = new UserInfo();
                User user = new User();
                userInfo.setUser(user);

                userInfo.setName(resultSetClient.getString(1));
                userInfo.setSurname(resultSetClient.getString(2));
                userInfo.setAddress(resultSetClient.getString(3));
                userInfo.setEmail(resultSetClient.getString(4));
                userInfo.setNumber(resultSetClient.getString(5));
                userInfo.getUser().setId(resultSetClient.getInt(6));
            }

        } catch (InterruptedException | ConnectionPoolException e) {
            e.toString();
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot find user by login", e);
        } finally {
            closeResultSet(resultSetClient);
            closeStatement(preparedStatement);
            if (connection != null) {
                try {
                    connectionPool.freeConnection(connection);
                } catch (SQLException | ConnectionPoolException e) {
                    //LOGGER.error("Can not free connection from connection pool");
                }
            }
        }

        return userInfo;
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


    public void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            //LOGGER.error("Can not close result set");
        }
    }
}
