//package com.dao.impl;
//
//import carShowroom.bean.model.Card;
//import carShowroom.dao.CardDAO;
//import carShowroom.dao.connectionMySQL.ConnectionPool;
//import carShowroom.dao.connectionMySQL.ConnectionPoolException;
//import carShowroom.dao.exception.DAOException;
//import org.apache.log4j.Logger;
//
//import java.sql.*;
//import java.util.List;
//
///**
// * Created by AndrewRaukut on 12/6/16.
// */
//public class MySQLCardDAO implements CardDAO {
//    private static final Logger LOGGER = Logger.getRootLogger();
//
//
//    private static final String INSERT_USER_CARD_QUERY = "INSERT INTO `cards` " +
//            "(`number`, `holder`, `date`, `company`, `user_id`) " +
//            "VALUES (?, ?, ?, ?, ?)";
//
//    private static final String UPDATE_USER_CARD_QUERY = "UPDATE `cards` " +
//            "SET `number` = ?, `holder` = ?, `date` = ?, `company` = ?" +
//            "WHERE `user_id` = ? ";
//
//    private static final String SELECT_CARD_BY_CLIENT_ID_QUERY = "SELECT * FROM `cards` WHERE `user_id` = ? ";
//
//    private static final String SELECT_ALL_CARDS = "SELECT * FROM `cards`";
//
//    private static final String DELETE_CARD_BY_ID_QUERY = "DELETE FROM `cards` WHERE `card_id` = ? ";
//
//
//    @Override
//    public int insert(Card card) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        int key = 0;
//        try{
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(INSERT_USER_CARD_QUERY, Statement.RETURN_GENERATED_KEYS);
//
//            preparedStatement.setLong(1, card.getCardNumber());
//            preparedStatement.setString(2, card.getCardHolder());
//            preparedStatement.setString(3, card.getCardDate());
//            preparedStatement.setString(4, card.getCardCompany());
//            preparedStatement.setInt(5, card.getUserID());
//
//            preparedStatement.executeUpdate();
//            resultSet = preparedStatement.getGeneratedKeys();
//            resultSet.next();
//            key = resultSet.getInt(1);
//
//            return key;
//        } catch (InterruptedException | ConnectionPoolException e) {
//            throw new DAOException("Cannot get a connection from Connection Pool", e);
//        } catch (SQLException e){
//            throw new DAOException("DAO layer: cannot insert card", e);
//        } finally {
//            closeStatement(preparedStatement);
//            closeResultSet(resultSet);
//            if (connection != null) {
//                try {
//                    connectionPool.freeConnection(connection);
//                } catch (SQLException | ConnectionPoolException e) {
//                    throw new DAOException("Cannot free a connection from Connection Pool", e);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void update(Card card) throws DAOException {
//
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try{
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(UPDATE_USER_CARD_QUERY);
//
//            preparedStatement.setLong(1, card.getCardNumber());
//            preparedStatement.setString(2, card.getCardHolder());
//            preparedStatement.setString(3, card.getCardDate());
//            preparedStatement.setString(4, card.getCardCompany());
//            preparedStatement.setInt(5, card.getId());
//
//            preparedStatement.executeUpdate();
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e){
//            throw new DAOException("DAO layer: cannot update passport", e);
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
//    public Card findById(int id) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Card card = new Card();
//        try{
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(SELECT_CARD_BY_CLIENT_ID_QUERY);
//
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.next()){
//                card.setId(resultSet.getInt(1));
//                card.setCardNumber(resultSet.getLong(2));
//                card.setCardHolder(resultSet.getString(3));
//                card.setCardDate(resultSet.getString(4));
//                card.setCardCompany(resultSet.getString(5));
//
//            }
//            else {
//                card.setCardCompany("");
//                card.setCardDate("");
//                card.setCardHolder("NONE");
//                card.setCardNumber(0000000000000000);
//                card.setId(0);
//            }
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e){
//            throw new DAOException("DAO layer: cannot find card by id", e);
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
//        return card;
//    }
//
//    @Override
//    public List<Card> findAll() throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Card card = null;
//        List<Card> cards= null;
//        try{
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(SELECT_ALL_CARDS);
//
//            resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.next()){
//                card = new Card();
//                card.setId(resultSet.getInt(1));
//                card.setCardNumber(resultSet.getInt(2));
//                card.setCardHolder(resultSet.getString(3));
//                card.setCardDate(resultSet.getString(4));
//                card.setCardCompany(resultSet.getString(5));
//                cards.add(card);
//            }
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e){
//            throw new DAOException("DAO layer: cannot find card by id", e);
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
//        return cards;
//    }
//
//    @Override
//    public void delete(int id) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try{
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(DELETE_CARD_BY_ID_QUERY);
//
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e){
//            throw new DAOException("DAO layer: cannot delete card", e);
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
//    public void closeStatement(Statement statement){
//        try {
//            if (statement != null) {
//                statement.close();
//            }
//        } catch (SQLException e){
//            LOGGER.error("Can not close statement");
//        }
//    }
//
//
//    public void closeResultSet(ResultSet resultSet){
//        try {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//        } catch (SQLException e){
//            LOGGER.error("Can not close result set");
//        }
//    }
//}
