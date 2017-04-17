package com.dao;

import com.dao.exception.DAOException;
import com.model.User;

import java.util.List;

/**
 * Created by AndrewRaukut on 12/6/16.
 */
public interface UserDAO {

    void insert(User user) throws DAOException;

//    void update(User user) throws DAOException;

//    void updateUserVisits(User user) throws DAOException;

    void delete(int id) throws DAOException;

    User findByLogin(String login) throws DAOException;

 //   User findByIdAndRole(int id, boolean isAdmin) throws DAOException;

 //   List<User> findAll() throws DAOException;
}
