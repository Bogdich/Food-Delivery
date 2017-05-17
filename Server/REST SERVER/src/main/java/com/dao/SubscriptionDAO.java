package com.dao;

import com.dao.exception.DAOException;

/**
 * Created by Adrienne on 26.04.17.
 */
public interface SubscriptionDAO {
    void addSub(int userId, int categoryId) throws DAOException;

    void deleteSub(int userId, int categoryId) throws DAOException;
}
