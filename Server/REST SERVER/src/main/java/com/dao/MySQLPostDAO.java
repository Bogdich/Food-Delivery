package com.dao;

import com.dao.connectionMySQL.ConnectionPool;
import com.dao.exception.ConnectionPoolException;
import com.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

/**
 * Parent class for interfaces
 * !Unused now!
 *
 * @param <T> entity
 */
public interface MySQLPostDAO<T> {
    Logger LOGGER = LogManager.getLogger();
    int UNSUPPORTED_PK_INSERT = -1;

    void update(T data) throws DAOException;
    int insert(T data) throws DAOException;
    void delete(T data) throws DAOException;

    default void close(AutoCloseable... resources){
        for(AutoCloseable resource : resources){
            if(resource != null){
                try {
                    resource.close();
                } catch(Exception e){
                    LOGGER.error(e);
                }
            }
        }
    }

/*    default void retrieve(Connection connection){
        if(connection != null){
            try {
                ConnectionPool.getInstance().retrieve(connection);
            } catch(ConnectionPoolException e){
                logger.error(e);
            }
        }
    }*/
}
