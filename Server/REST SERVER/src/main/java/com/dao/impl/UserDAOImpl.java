package com.dao.impl;

import com.dao.UserDAO;
import com.dao.connectionMySQL.ConnectionPool;
import com.dao.exception.ConnectionPoolException;
import com.dao.exception.DAOException;
import com.entity.User;
import com.entity.UserInfo;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    //private static final Logger LOGGER = Logger.getRootLogger();

    private static final String INSERT_USER_QUERY = "INSERT INTO `user` " +
            "(`login`, `password`) " +
            "VALUES (?, ?)";


    private static final String UPDATE_VIS_NUMB_BY_USER_ID = "UPDATE `users` SET `visitsNumber` = ? WHERE `user_id` = ?";

    private static final String SELECT_ADMINISTRATOR_BY_LOGIN_QUERY = "SELECT * FROM `admins` WHERE `login` = ? ";

    private static final String SELECT_USER_BY_LOGIN_QUERY = "SELECT * FROM `user` WHERE `login` = ? ";

    private static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM `users` WHERE `user_id` = ? ";

    private static final String SELECT_ADMINISTRATOR_BY_ID_QUERY = "SELECT * FROM `admins` WHERE `admin_id` = ? ";

    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM `users` ";

    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM `user` WHERE `id` = ? ";

    @Override
    public int insert(User user) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
            rs.close();

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

        return id;
    }

    @Override
    public int login(String login, String pass) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String password = null;
        int id = 0;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_QUERY);

            preparedStatement.setString(1, login);


            ResultSet rs = preparedStatement.executeQuery();
            if (rs == null) {
                throw new DAOException("DAO layer: no user with this login");
            }
            if (rs.next()){
                id = rs.getInt("id");
                password = rs.getString("password");
            }
            rs.close();
            if(!pass.equals(password)) {
                throw new DAOException("DAO layer: incorrect password");
            }
        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot login", e);
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
    public void updateUser(UserInfo userInfo) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY);
//
//            preparedStatement.setString(1, userInfo.getUser().getLogin());
//            preparedStatement.setString(2, userInfo.getUser().getPassword());
//            preparedStatement.setString(3, userInfo.getName());
//            preparedStatement.setString(4, userInfo.getSurname());
//            preparedStatement.setString(5, userInfo.getAddress());
//            preparedStatement.setString(6, userInfo.getEmail());
//            preparedStatement.setString(7, userInfo.getNumber());
//            preparedStatement.setInt(8, userInfo.getUser().getId());
//
//
//            preparedStatement.executeQuery();
//        } catch (InterruptedException | ConnectionPoolException e) {
//            //LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot updateUserInfo user", e);
//        } finally {
//            closeStatement(preparedStatement);
//            if (connection != null) {
//                try {
//                    connectionPool.freeConnection(connection);
//                } catch (SQLException | ConnectionPoolException e) {
//                    //LOGGER.error("Can not free connection from connection pool");
//                }
//            }
//        }
    }

//    @Override
//    public void updateUserVisits(User user) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = connectionPool.getConnection();
//            preparedStatement = connection.prepareStatement(UPDATE_VIS_NUMB_BY_USER_ID);
//
//            preparedStatement.setInt(1, user.getVisitsNumber());
//            preparedStatement.setInt(2, user.getResponseID());
//
//            preparedStatement.executeUpdate();
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot updateUserInfo user visits", e);
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

    @Override
    public void delete(int id) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (InterruptedException | ConnectionPoolException e) {
            //LOGGER.error("Can not get connection from connection pool");
        } catch (SQLException e) {
            throw new DAOException("DAO layer: cannot delete user", e);
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

//    @Override
//    public User findByLogin(String login) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatementAdministrator = null;
//        ResultSet resultSetAdministrator = null;
//        PreparedStatement preparedStatementClient = null;
//        ResultSet resultSetClient = null;
//        User user = null;
//        UserInfo userInfo = null;
//        try {
//            connection = connectionPool.getConnection();
//            preparedStatementAdministrator = connection.prepareStatement(SELECT_ADMINISTRATOR_BY_LOGIN_QUERY);
//
//            preparedStatementAdministrator.setString(1, login);
//            resultSetAdministrator = preparedStatementAdministrator.executeQuery();
//
//            if (!resultSetAdministrator.next()) {
//                preparedStatementClient = connection.prepareStatement(SELECT_USER_BY_LOGIN_QUERY);
//
//                preparedStatementClient.setString(1, login);
//                resultSetClient = preparedStatementClient.executeQuery();
//
//                if (resultSetClient.next()) {
//                    user = new User();
//                    user.setResponseID(resultSetClient.getInt(1));
//                    user.setLogin(resultSetClient.getString(2));
//                    user.setPassword(resultSetClient.getString(3));
//                    user.getUserInfo().setName(resultSetClient.getString(4));
//                    user.getUserInfo().setSurname(resultSetClient.getString(5));
//                    user.getUserInfo().setEmail(resultSetClient.getString(6));
//                    userInfo = new UserInfo();
//                    card.setResponseID(resultSetClient.getInt(7));
//                    user.setCard(card);
//                    user.setAdmin(false);
//                    user.setVisitsNumber(resultSetClient.getInt(8));
//                }
//            } else {
//                user = new User();
//                user.setResponseID(resultSetAdministrator.getInt(1));
//                user.setLogin(resultSetAdministrator.getString(2));
//                user.setPassword(resultSetAdministrator.getString(3));
//                user.setName(resultSetAdministrator.getString(4));
//                user.setSurname(resultSetAdministrator.getString(5));
//                user.setEmail(resultSetAdministrator.getString(6));
//                user.setAdmin(true);
//                user.setCard(null);
//            }
//
//        } catch (InterruptedException | ConnectionPoolException e) {
//            //LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot find user by login", e);
//        } finally {
//            closeResultSet(resultSetAdministrator);
//            closeStatement(preparedStatementAdministrator);
//            closeResultSet(resultSetClient);
//            closeStatement(preparedStatementClient);
//            if (connection != null) {
//                try {
//                    connectionPool.freeConnection(connection);
//                } catch (SQLException | ConnectionPoolException e) {
//                    //LOGGER.error("Can not free connection from connection pool");
//                }
//            }
//        }
//
//        return user;
//    }

//    @Override
//    public User findByIdAndRole(int id, boolean isAdmin) throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        User user = null;
//        try {
//            connection = connectionPool.getConnection();
//            if (isAdmin) {
//                preparedStatement = connection.prepareStatement(SELECT_ADMINISTRATOR_BY_ID_QUERY);
//            } else {
//                preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_QUERY);
//            }
//
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                user = new User();
//                Card card = new Card();
//                card.setResponseID(resultSet.getInt(4));
//                user.setCard(card);
//                user.setResponseID(resultSet.getInt(1));
//                user.setLogin(resultSet.getString(2));
//                user.setPassword(resultSet.getString(3));
//                user.setName(resultSet.getString(4));
//                user.setSurname(resultSet.getString(5));
//                user.setEmail(resultSet.getString(6));
//                if (!isAdmin) {
//                    user.setVisitsNumber(resultSet.getInt(7));
//                }
//            }
//
//        } catch (InterruptedException | ConnectionPoolException e) {
//            LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot find user by id", e);
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
//        return user;
//    }

//    @Override
//    public List<User> findAll() throws DAOException {
//        ConnectionPool connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        List<User> users = new ArrayList<>();
//        try {
//            connection = connectionPool.getConnection();
//            statement = connection.createStatement();
//
//            resultSet = statement.executeQuery(SELECT_ALL_USERS_QUERY);
//
//            while (resultSet.next()) {
//                User user = new User();
//                Card card = new Card();
//                card.setResponseID(resultSet.getInt(7));
//                user.setCard(card);
//                user.setResponseID(resultSet.getInt(1));
//                user.setLogin(resultSet.getString(2));
//                user.setPassword(resultSet.getString(3));
//                user.setName(resultSet.getString(4));
//                user.setSurname(resultSet.getString(5));
//                user.setEmail(resultSet.getString(6));
//                user.setVisitsNumber(resultSet.getInt(8));
//
//                users.add(user);
//            }
//
//        } catch (InterruptedException | ConnectionPoolException e) {
//           // LOGGER.error("Can not get connection from connection pool");
//        } catch (SQLException e) {
//            throw new DAOException("DAO layer: cannot select all users", e);
//        } finally {
//            closeResultSet(resultSet);
//            closeStatement(statement);
//            if (connection != null) {
//                try {
//                    connectionPool.freeConnection(connection);
//                } catch (SQLException | ConnectionPoolException e) {
//                    //LOGGER.error("Can not free connection from connection pool");
//                }
//            }
//        }
//
//        return users;
//    }

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
