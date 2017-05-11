package com.dao.impl;

import com.dao.DishDAO;
import com.dao.connectionMySQL.ConnectionPool;
import com.dao.exception.ConnectionPoolException;
import com.dao.exception.DAOException;
import com.entity.Category;
import com.entity.Dish;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrienne on 26.04.17.
 */
public class DishDAOImpl implements DishDAO{

    private static final String INSERT_DISH_QUERY = "INSERT INTO `dish` " +
            "(`name`, `description`, `weight`, `price`, `category_id`, `image_URL`) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_DISH_QUERY = "UPDATE `users` " +
            "SET `login` = ?, `password` = ?, `name` = ?, `surname` = ?, `email` = ?, `card_id` = ? " +
            "WHERE `user_id` = ?";

    private static final String SELECT_DISH_BY_ID_QUERY = "SELECT * FROM `dish` WHERE `id` = ? ";

    private static final String SELECT_DISH_BY_CATEGORY_ID_QUERY = "SELECT * FROM `dish` WHERE `category_id` = ? ";

    private static final String DELETE_DISH_BY_ID_QUERY = "DELETE FROM `dish` WHERE `id` = ? ";

    @Override
    public int insert(Dish dish) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_DISH_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, dish.getName());
            preparedStatement.setString(2, dish.getDescription());
            preparedStatement.setInt(3, dish.getWeight());
            preparedStatement.setBigDecimal(4, dish.getPrice());
            preparedStatement.setInt(5, dish.getCategory().getId());
            preparedStatement.setString(6, dish.getImageURL());


            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
            rs.close();

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot insertOrder dish", e);
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
            preparedStatement = connection.prepareStatement(DELETE_DISH_BY_ID_QUERY);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot delete dish", e);
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
    public Dish findById(int id) throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Dish dish = null;
        try {
            connection = connectionPool.getConnection();

            preparedStatement = connection.prepareStatement(SELECT_DISH_BY_ID_QUERY);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                dish = new Dish();
                Category category = new Category();

                category.setId(resultSet.getInt(6));

                dish.setCategory(category);
                dish.setId(id);
                dish.setName(resultSet.getString(2));
                dish.setDescription(resultSet.getString(3));
                dish.setWeight(resultSet.getInt(4));
                dish.setPrice(resultSet.getBigDecimal(5));
                dish.setImageURL(resultSet.getString(6));
            }

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot find dish by id", e);
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

        return dish;

    }

    @Override
    public List<Dish> findDishesForCategoryId(int categoryId) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        List<Dish> dishes = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_DISH_BY_CATEGORY_ID_QUERY);

            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Dish dish = new Dish();
                Category category = new Category();

                dish.setId(resultSet.getInt(1));
                dish.setName(resultSet.getString(2));
                dish.setDescription(resultSet.getString(3));
                dish.setWeight(resultSet.getInt(4));
                dish.setPrice(resultSet.getBigDecimal(5));
                dish.setImageURL(resultSet.getString(7));
                category.setId(categoryId);

                dishes.add(dish);
            }

        } catch (InterruptedException | ConnectionPoolException e) {
           // LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot select all dishes by category id", e);
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

        return dishes;
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
