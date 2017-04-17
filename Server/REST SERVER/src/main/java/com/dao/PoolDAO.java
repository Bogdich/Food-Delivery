package com.dao;


import com.dao.exception.DAOException;

public interface PoolDAO {

    void init() throws DAOException;

    void destroy() throws DAOException;
}
