package org.example.service;

import org.example.DAO.UserDAOImpl;
import org.example.model.User;

import java.sql.SQLException;

public class UserService {
    private UserDAOImpl userDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public boolean registerUser(User user) {
        try {
            return userDAO.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User loginUser(String email, String password) {
        try {
            return userDAO.getUserByEmailAndPassword(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

