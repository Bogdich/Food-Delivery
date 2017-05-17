package com.dao;

import com.dao.exception.DAOException;
import com.entity.User;

/**
 * Created by Adrienne on 26.04.17.
 */
public interface OderDAO {
    int insert(String date) throws DAOException;
}
