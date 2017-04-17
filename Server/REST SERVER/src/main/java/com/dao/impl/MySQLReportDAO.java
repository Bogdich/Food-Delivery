//package com.dao.impl;
//
//import carShowroom.bean.model.BuyReport;
//import carShowroom.bean.model.Car;
//import carShowroom.bean.model.CarParts;
//import carShowroom.dao.ReportDAO;
//import carShowroom.dao.connectionMySQL.ConnectionPool;
//import carShowroom.dao.connectionMySQL.ConnectionPoolException;
//import carShowroom.dao.exception.DAOException;
//import org.apache.log4j.Logger;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by AndrewRaukut on 12/6/16.
// */
//public class MySQLReportDAO implements ReportDAO {
//
//    Logger LOGGER = Logger.getRootLogger();
//
//    private static final String INSERT_REPORT_QUERY = "INSERT INTO `buyreports` " +
//            "(`user_name_surname`, `userLogin`, `userCard`, `price`, `mark`, `model`, `gearbox`, `transmission`, `color`, `date`) " +
//            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
//
//    private static final String SELECT_CAR_BY_ID_QUERY = "SELECT * FROM `cars` WHERE `id_car` = ?";
//
//    private static final String SELECT_ALL_REPORTS_QUERY = "SELECT * FROM `buyreports`";
//
//    private static final String DELETE_REPORT_BY_ID_QUERY = "DELETE FROM `buyreports` WHERE `report_id` = ? ";
//
//    @Override
//    public void insert(BuyReport buyReport) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(INSERT_REPORT_QUERY);
//
//            preparedStatement.setString(1, buyReport.getUserNameAndSurname());
//            preparedStatement.setString(2, buyReport.getUserLogin());
//            preparedStatement.setString(3, buyReport.getUserCard());
//            preparedStatement.setString(4, String.valueOf(buyReport.getCar().getPrice()));
//            preparedStatement.setString(5, buyReport.getCar().getMark().getName());
//            preparedStatement.setString(6, buyReport.getCar().getModel().getName());
//            preparedStatement.setString(7, buyReport.getCar().getGearbox().getName());
//            preparedStatement.setString(8, buyReport.getCar().getTransmission().getName());
//            preparedStatement.setString(9, buyReport.getCar().getColor().getName());
//            preparedStatement.setDate(10, new Date(buyReport.getDate().getTime()));
//
//            preparedStatement.executeUpdate();
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot insert report", e);
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
//            preparedStatement = connection.prepareStatement(DELETE_REPORT_BY_ID_QUERY);
//
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot delete report", e);
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
//    public List<BuyReport> findAll() throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        List<BuyReport> buyReports = new ArrayList<>();
//
//        try {
//            connection = connectionPool.getConnection();
//            statement = connection.createStatement();
//
//            resultSet = statement.executeQuery(SELECT_ALL_REPORTS_QUERY);
//
//            while (resultSet.next()) {
//                CarParts mark = new CarParts();
//                CarParts model = new CarParts();
//                CarParts transmission = new CarParts();
//                CarParts gearbox = new CarParts();
//                CarParts color = new CarParts();
//                mark.setName(resultSet.getString(6));
//                model.setName(resultSet.getString(7));
//                gearbox.setName(resultSet.getString(8));
//                transmission.setName(resultSet.getString(9));
//                color.setName(resultSet.getString(10));
//                Car car = new Car();
//                car.setMark(mark);
//                car.setGearbox(gearbox);
//                car.setModel(model);
//                car.setTransmission(transmission);
//                car.setColor(color);
//                car.setPrice(resultSet.getInt(5));
//
//                BuyReport buyReport = new BuyReport();
//                buyReport.setCar(car);
//                buyReport.setId(resultSet.getInt(1));
//                buyReport.setUserNameAndSurname(resultSet.getString(2));
//                buyReport.setUserLogin(resultSet.getString(3));
//                buyReport.setUserCard(resultSet.getString(4));
//                buyReport.setDate(resultSet.getDate(11));
//                buyReports.add(buyReport);
//            }
//
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot select all buyReports", e);
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
//        return buyReports;
//    }
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
