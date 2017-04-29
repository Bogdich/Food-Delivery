package com.dao.impl;


import com.dao.CategoryDAO;
import com.dao.connectionMySQL.ConnectionPool;
import com.dao.exception.ConnectionPoolException;
import com.dao.exception.DAOException;
import com.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrienne on 26.04.17.
 */
public class CategoryDAOImpl implements CategoryDAO {

    private static final String INSERT_CATEGORY_QUERY = "INSERT INTO `category` " +
            "(`name`) " + "VALUES (?)";

    private static final String UPDATE_DISH_QUERY = "UPDATE `users` " +
            "SET `login` = ?, `password` = ?, `name` = ?, `surname` = ?, `email` = ?, `card_id` = ? " +
            "WHERE `user_id` = ?";

    private static final String SELECT_ALL_CATEGORIES_QUERY = "SELECT * FROM `category` ";


    private static final String SELECT_DISH_BY_ID_QUERY = "SELECT * FROM `category` WHERE `id` = ? ";

    private static final String SELECT_DISH_BY_CATEGORY_ID_QUERY = "SELECT * FROM `dish` WHERE `category_id` = ? ";

    private static final String DELETE_CATEGORY_BY_ID_QUERY = "DELETE FROM `category` WHERE `id` = ? ";

    @Override
    public int insert(Category category) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_CATEGORY_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, category.getName());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
            rs.close();

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot insert category", e);
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
    public void delete(int id) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_CATEGORY_BY_ID_QUERY);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot delete category", e);
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
    public Category findById(int id) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Category category = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SELECT_DISH_BY_ID_QUERY);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                category = new Category();

                category.setName(resultSet.getString(2));
                category.setId(resultSet.getInt(1));

            }

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot find category by id", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(preparedStatement);
            if (connection != null) {
                try {
                    connectionPool.freeConnection(connection);
                } catch (SQLException | ConnectionPoolException e) {
                    //LOGGER.error("Can not free connection from connection pool");
                }
            }
        }

        return category;

    }

    @Override
    public List<Category> findAll() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Category> categories = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SELECT_ALL_CATEGORIES_QUERY);

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));

                categories.add(category);
            }

        } catch (InterruptedException | ConnectionPoolException e) {
           // LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot select all categories", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            if (connection != null) {
                try {
                    connectionPool.freeConnection(connection);
                } catch (SQLException | ConnectionPoolException e) {
                    //LOGGER.error("Can not free connection from connection pool");
                }
            }
        }

        return categories;
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
