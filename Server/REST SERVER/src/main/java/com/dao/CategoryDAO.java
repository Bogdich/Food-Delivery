package com.dao;

import com.dao.exception.DAOException;
import com.entity.Category;

import java.util.List;

/**
 * Created by Adrienne on 26.04.17.
 */
public interface CategoryDAO {

    int insert(Category category) throws DAOException;

    void delete(int id) throws DAOException;

    Category findById(int id) throws DAOException;

    List<Category> findAll() throws DAOException;

}
