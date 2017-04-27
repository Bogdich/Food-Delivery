package com.dao;

import com.dao.exception.DAOException;
import com.entity.User;
import com.entity.UserInfo;

/**
 * Created by Adrienne on 26.04.17.
 */
public interface UserInfoDAO {

    void insert(UserInfo info) throws DAOException;

    void update(UserInfo info) throws DAOException;

    void deleteByUserId(int userId) throws DAOException;

    UserInfo findByUserId(int userId) throws DAOException;
}
