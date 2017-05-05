package com.dao;

import com.dao.exception.DAOException;
import com.entity.User;
import com.entity.UserInfo;


/**
 * Created by AndrewRaukut on 12/6/16.
 */
public interface UserDAO {

    int insert(User user) throws DAOException;

    void updateUser(UserInfo userInfo) throws DAOException;

//    void updateUserVisits(User user) throws DAOException;

    int login(String login, String pass) throws DAOException;

    void delete(int id) throws DAOException;

//    User findByLogin(String login) throws DAOException;

 //   User findByIdAndRole(int id, boolean isAdmin) throws DAOException;

 //   List<User> findAll() throws DAOException;
}
