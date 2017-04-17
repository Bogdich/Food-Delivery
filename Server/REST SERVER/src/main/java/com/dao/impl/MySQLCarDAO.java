//package com.dao.impl;
//
//import com.dao.CarDAO;
//import com.dao.connectionMySQL.ConnectionPool;
//import com.dao.connectionMySQL.ConnectionPoolException;
//import com.dao.exception.DAOException;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by AndrewRaukut on 12/6/16.
// */
//public class MySQLCarDAO implements CarDAO {
//
//    //Logger LOGGER = Logger.getRootLogger();
//
//    private static final String INSERT_CAR_QUERY = "INSERT INTO `cars` " +
//            "(`mark_id`, `model_id`, `transmission_id`, `gearbox_id`, `color_id`, `mileage`, `year`, `price`, `info`) " +
//            "VALUES (?, ?, ?, ?, ?, ?, ?, ? , ?) ";
//
//    private static final String INSERT_MARK_QUERY = "INSERT INTO `mark`  (`name`) VALUES (?) ";
//
//    private static final String INSERT_MODEL_QUERY = "INSERT INTO `model`  (`name`) VALUES (?) ";
//
//    private static final String INSERT_COLOR_QUERY = "INSERT INTO `color`  (`name`) VALUES (?) ";
//
//    private static final String UPDATE_CAR_QUERY = "UPDATE `cars` " +
//            "SET `mark_id` = ?, `model_id` = ?, `transmission_id` = ?, `gearbox_id` = ?, `color_id` = ?, `mileage` = ?, `year` = ?, `price` = ?, `info` = ? " +
//            "WHERE `car_id` = ?";
//
//    private static final String SELECT_MARK_BY_NAME_QUERY = "SELECT * FROM `mark` WHERE `name` = ?";
//
//    private static final String SELECT_MODEL_BY_NAME_QUERY = "SELECT * FROM `model` WHERE `name` = ?";
//
//    private static final String SELECT_GEARBOX_BY_NAME_QUERY = "SELECT * FROM `gearbox` WHERE `name` = ?";
//
//    private static final String SELECT_TRANSMISSION_BY_NAME_QUERY = "SELECT * FROM `transmission` WHERE `name` = ?";
//
////    private static final String SELECT_PART_BY_NAME_QUERY = "SELECT * FROM `` WHERE `name` = ?";
//
//    private static final String SELECT_COLOR_BY_NAME_QUERY = "SELECT * FROM `color` WHERE `name` = ?";
//
//    private static final String SELECT_ALL_CARS_QUERY = "SELECT * FROM `cars`";
//
//    private static final String DELETE_CAR_BY_ID_QUERY = "DELETE FROM `cars` WHERE `car_id` = ? ";
//
//
//    @Override
//    public void insert(Car car) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(INSERT_CAR_QUERY);
//
//            car = fillIdForParts(car);
//            preparedStatement.setInt(1, car.getMark().getId());
//            preparedStatement.setInt(2, car.getModel().getId());
//            preparedStatement.setInt(3, car.getTransmission().getId());
//            preparedStatement.setInt(4, car.getGearbox().getId());
//            preparedStatement.setInt(5, car.getColor().getId());
//            preparedStatement.setInt(6, car.getMileage());
//            preparedStatement.setInt(7, car.getYear());
//            preparedStatement.setInt(8, car.getPrice());
//            preparedStatement.setString(9, car.getInfo());
//
//
//
//            preparedStatement.executeUpdate();
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot insert car", e);
//        } finally {
//            closeStatement(preparedStatement);
//            if (connection != null) {
//                try {
//                    connectionPool.freeConnection(connection);
//                } catch (SQLException | ConnectionPoolException e) {
//                    LOGGER.error("Can not free connection from connection pool");
//                }
//            }
//        }
//    }
//
//    @Override
//    public void update(Car car) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(UPDATE_CAR_QUERY);
//
//            car = fillIdForParts(car);
//            preparedStatement.setInt(1, car.getMark().getId());
//            preparedStatement.setInt(2, car.getModel().getId());
//            preparedStatement.setInt(3, car.getTransmission().getId());
//            preparedStatement.setInt(4, car.getGearbox().getId());
//            preparedStatement.setInt(5, car.getColor().getId());
//            preparedStatement.setInt(6, car.getMileage());
//            preparedStatement.setInt(7, car.getYear());
//            preparedStatement.setInt(8, car.getPrice());
//            preparedStatement.setString(9, car.getInfo());
//            preparedStatement.setInt(10, car.getId());
//
//            preparedStatement.executeUpdate();
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot update car", e);
//        } finally {
//            closeStatement(preparedStatement);
//            if (connection != null) {
//                try {
//                    connectionPool.freeConnection(connection);
//                } catch (SQLException | ConnectionPoolException e) {
//                    LOGGER.error("Can not free connection from connection pool");
//                }
//            }
//        }
//    }
//
//    @Override
//    public void delete(int id) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(DELETE_CAR_BY_ID_QUERY);
//
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot delete car", e);
//        } finally {
//            closeStatement(preparedStatement);
//            if (connection != null) {
//                try {
//                    connectionPool.freeConnection(connection);
//                } catch (SQLException | ConnectionPoolException e) {
//                    LOGGER.error("Can not free connection from connection pool");
//                }
//            }
//        }
//    }
//
//    @Override
//    public List<Car> findAll() throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        List<Car> cars = new ArrayList<>();
//        try {
//            connection = connectionPool.getConnection();
//            statement = connection.createStatement();
//
//            resultSet = statement.executeQuery(SELECT_ALL_CARS_QUERY);
//
//            while (resultSet.next()) {
//                Car car = new Car();
//                car.setId(resultSet.getInt(1));
//                car.setMark(findParts("mark", resultSet.getInt(2)));
//                car.setModel(findParts("model", resultSet.getInt(3)));
//                car.setTransmission(findParts("transmission", resultSet.getInt(4)));
//                car.setGearbox(findParts("gearbox", resultSet.getInt(5)));
//                car.setColor(findParts("color", resultSet.getInt(6)));
//                car.setMileage(resultSet.getInt(7));
//                car.setYear(resultSet.getInt(8));
//                car.setPrice(resultSet.getInt(9));
//                car.setInfo(resultSet.getString(10));
//
//                cars.add(car);
//            }
//
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot select all cars", e);
//        } finally {
//            closeResultSet(resultSet);
//            closeStatement(statement);
//            if (connection != null) {
//                try {
//                    connectionPool.freeConnection(connection);
//                } catch (SQLException | ConnectionPoolException e) {
//                    LOGGER.error("Can not free connection from connection pool");
//                }
//            }
//        }
//
//        return cars;
//    }
//
//    private CarParts findParts(String table, int id) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        CarParts part = new CarParts();
//        try {
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement("SELECT * FROM `" + table + "` WHERE `" + table + "_id` = ?");
//
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                part.setId(resultSet.getInt(1));
//                part.setName(resultSet.getString(2));
//            }
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot find part by id", e);
//        } finally {
//            closeResultSet(resultSet);
//            closeStatement(preparedStatement);
//            if (connection != null) {
//                try {
//                    connectionPool.freeConnection(connection);
//                } catch (SQLException | ConnectionPoolException e) {
//                    LOGGER.error("Can not free connection from connection pool");
//                }
//            }
//        }
//
//        return part;
//    }
//
//    private Car fillIdForParts(Car car) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        ResultSet resultSetMark = null;
//        ResultSet resultSetModel = null;
//        ResultSet resultSetColor = null;
//        ResultSet resultSetGearbox = null;
//        ResultSet resultSetTransmission = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = connectionPool.getConnection();
//
//
//            preparedStatement = connection.prepareStatement(SELECT_MARK_BY_NAME_QUERY);
//            preparedStatement.setString(1, car.getMark().getName());
//            resultSetMark = preparedStatement.executeQuery();
//
//            preparedStatement = connection.prepareStatement(SELECT_MODEL_BY_NAME_QUERY);
//            preparedStatement.setString(1, car.getModel().getName());
//            resultSetModel = preparedStatement.executeQuery();
//
//            preparedStatement = connection.prepareStatement(SELECT_COLOR_BY_NAME_QUERY);
//            preparedStatement.setString(1, car.getColor().getName());
//            resultSetColor = preparedStatement.executeQuery();
//
//            preparedStatement = connection.prepareStatement(SELECT_GEARBOX_BY_NAME_QUERY);
//            preparedStatement.setString(1, car.getGearbox().getName());
//            resultSetGearbox = preparedStatement.executeQuery();
//            if (resultSetGearbox.next()) car.getGearbox().setId(resultSetGearbox.getInt(1));
//
//
//            preparedStatement = connection.prepareStatement(SELECT_TRANSMISSION_BY_NAME_QUERY);
//            preparedStatement.setString(1, car.getTransmission().getName());
//            resultSetTransmission = preparedStatement.executeQuery();
//            if (resultSetTransmission.next()) car.getTransmission().setId(resultSetTransmission.getInt(1));
//
//
//            if (!resultSetMark.next()) {
//                preparedStatement = connection.prepareStatement(INSERT_MARK_QUERY, Statement.RETURN_GENERATED_KEYS);
//                preparedStatement.setString(1, car.getMark().getName());
//                preparedStatement.executeUpdate();
//
//                resultSetMark = preparedStatement.getGeneratedKeys();
//                resultSetMark.next();
//                car.getMark().setId(resultSetMark.getInt(1));
//            } else {
//                car.getMark().setId(resultSetMark.getInt(1));
//            }
//
//            if (!resultSetModel.next()) {
//                preparedStatement = connection.prepareStatement(INSERT_MODEL_QUERY, Statement.RETURN_GENERATED_KEYS);
//                preparedStatement.setString(1, car.getModel().getName());
//                preparedStatement.executeUpdate();
//
//                resultSetModel = preparedStatement.getGeneratedKeys();
//                resultSetModel.next();
//                car.getModel().setId(resultSetModel.getInt(1));
//            } else {
//                car.getModel().setId(resultSetModel.getInt(1));
//            }
//
//            if (!resultSetColor.next()) {
//                preparedStatement = connection.prepareStatement(INSERT_COLOR_QUERY, Statement.RETURN_GENERATED_KEYS);
//                preparedStatement.setString(1, car.getColor().getName());
//                preparedStatement.executeUpdate();
//
//                resultSetColor = preparedStatement.getGeneratedKeys();
//                resultSetColor.next();
//                car.getColor().setId(resultSetColor.getInt(1));
//            } else {
//                car.getColor().setId(resultSetColor.getInt(1));
//            }
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot fill id for part", e);
//        } finally {
//            closeResultSet(resultSetColor);
//            closeResultSet(resultSetGearbox);
//            closeResultSet(resultSetMark);
//            closeResultSet(resultSetModel);
//            closeResultSet(resultSetTransmission);
//            closeStatement(preparedStatement);
//            if (connection != null) {
//                try {
//                    connectionPool.freeConnection(connection);
//                } catch (SQLException | ConnectionPoolException e) {
//                    LOGGER.error("Can not free connection from connection pool");
//                }
//            }
//        }
//
//        return car;
//    }
//
//
//    public void closeStatement(Statement statement) {
//        try {
//            if (statement != null) {
//                statement.close();
//            }
//        } catch (SQLException e) {
//            LOGGER.error("Can not close statement");
//        }
//    }
//
//
//    public void closeResultSet(ResultSet resultSet) {
//        try {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//        } catch (SQLException e) {
//            LOGGER.error("Can not close result set");
//        }
//    }
//}
