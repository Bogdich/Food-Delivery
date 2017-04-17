package com.dao.impl;

import com.dao.PoolDAO;
import com.dao.connectionMySQL.ConnectionPool;
import com.dao.connectionMySQL.ConnectionPoolException;
import com.dao.exception.DAOException;

public class MySQLPoolDAO implements PoolDAO {

    @Override
    public void init() throws DAOException {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.init();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Cannot init a mySQL connection pool", e);
        }
    }

    @Override
    public void destroy() throws DAOException {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.destroy();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Cannot destroy a mySQL connection pool", e);
        }
    }
}
