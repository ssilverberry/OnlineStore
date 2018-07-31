package com.company.store.model.services;

import com.company.store.model.dao.UserDAO;
import com.company.store.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

@Component
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUser(String email, String password){
        return userDAO.getByCredentials(email, password);
    }

    public boolean match(String email, String password) {
        User user = userDAO.getByCredentials(email, password);
        return user.getEmail().equals(email) && user.getPassword().equals(password);
    }

    public Collection<User> getAll () {
        return userDAO.getAllUsers();
    }

    public String validateUserType(User user){
        if (user.getIsAdmin()){
            return "admin";
        } else {
            return "user";
        }
    }
}
