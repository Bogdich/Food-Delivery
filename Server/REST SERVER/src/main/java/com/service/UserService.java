package com.service;

import com.dao.UserDAO;
import com.dao.UserInfoDAO;
import com.dao.exception.DAOException;
import com.dao.factory.DAOFactory;
import com.entity.User;
import com.entity.UserInfo;

/**
 * Created by KirillBudevich on 27.04.17.
 */
public class UserService {

    public int insertUser(String login, String password, String name, String surname, String number, String address, String email)/* throws ServiceException*/ {
//        if(!Validator.validateLogin(login)){
//            throw new ServiceWrongLoginException("Wrong login");
//        }
//        if(!Validator.validatePassword(password)){
//            throw new ServiceWrongPasswordException("Wrong password");
//        }
//        if(!Validator.validateInt(id) || !Validator.validateString(email, EMAIL_MAX_LENGTH) ||
//                !Validator.validateString(surname, SURNAME_MAX_LENGTH) ||
//                !Validator.validateString(name, NAME_MAX_LENGTH)){
//            throw new ServiceException("Wrong parameters for registration");
//        }
//        if(!Validator.validateEmail(email)){
//            throw new ServiceWrongPasswordException("Wrong email");
//        }

        DAOFactory factory = DAOFactory.getInstance(DAOFactory.Factories.MYSQL);
        UserDAO userDAO = factory.getUserDAO();
        UserInfoDAO userInfoDAO = factory.getUserInfoDAO();

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        UserInfo userInfo;

        int id = 0;
        try {
            user.setId(userDAO.insert(user));
            id = user.getId();
            userInfo = new UserInfo(user, name, surname, number, address, email);
            userInfoDAO.insert(userInfo);

        } catch (DAOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int login(String login, String password) {
        DAOFactory factory = DAOFactory.getInstance(DAOFactory.Factories.MYSQL);
        UserDAO userDAO = factory.getUserDAO();

        int id = 0;
        try {
            id = userDAO.login(login, password);

        } catch (DAOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public UserInfo getUserInfo(int userId)/* throws ServiceException*/ {

        DAOFactory factory = DAOFactory.getInstance(DAOFactory.Factories.MYSQL);
        UserInfoDAO userInfoDAO = factory.getUserInfoDAO();

        UserInfo userInfo = null;

        try {
            userInfo = userInfoDAO.findByUserId(userId);

        } catch (DAOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public void updateUserInfo(String login, String password, String name, String surname, String number, String address, String email, int userId){

        DAOFactory factory = DAOFactory.getInstance(DAOFactory.Factories.MYSQL);
        UserInfoDAO userInfoDAO = factory.getUserInfoDAO();

        try {
            User user = new User(userId, login, password, false);
            UserInfo userInfo = new UserInfo(user, name, surname, number, address, email);
            userInfoDAO.updateUserInfo(userInfo);

        } catch (DAOException e) {
            e.printStackTrace();
        }
//        return userInfo;
    }

    public void deleteUser(int id) /* throws ServiceException*/ {
//        if(!Validator.validateInt(id)){
//            throw new ServiceException("Wrong id");
//        }

        DAOFactory factory = DAOFactory.getInstance(DAOFactory.Factories.MYSQL);
        com.dao.UserDAO userDAO = factory.getUserDAO();

        try {
            userDAO.delete(id);
        } catch (DAOException e) {
//            throw new ServiceException("Service layer: cannot delete user", e);
        }
    }
}
