package com.service;

import com.dao.PoolDAO;
import com.dao.exception.DAOException;
import com.dao.factory.DAOFactory;

/**
 * Created by KirillBudevich on 27.04.17.
 */
public class DAOService {

    public void init() {//throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance(DAOFactory.Factories.MYSQL);
            PoolDAO poolDAO = daoFactory.getPoolDAO();
            poolDAO.init();
        } catch (DAOException e) {
            // throw new ServiceException("Cannot init a pool", e);
        }
    }

    public void destroy() {//throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance(DAOFactory.Factories.MYSQL);
            PoolDAO poolDAO = daoFactory.getPoolDAO();
            poolDAO.destroy();
        } catch (DAOException e) {
            //throw new ServiceException("Cannot destroy a pool", e);
        }
    }
}
