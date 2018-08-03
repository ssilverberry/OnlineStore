package com.company.store.services.impl;

import com.company.store.repository.UserDAO;
import com.company.store.entities.User;
import com.company.store.entities.UserRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUser(String email, String password){
        return userDAO.getByCredentials(email, password);
    }
    /**
     * Acceptable.
     * The method is used for matching needed user from Spring form with existing one in DB.
    */
    public boolean match(String email, String password) {
        User user = userDAO.getByCredentials(email, password);
        if (user != null)
            return user.getEmail().equals(email) && user.getPassword().equals(password);
        return false;
    }

    public Collection<User> getAll () {
        return userDAO.getAllUsers();
    }

    public UserRoles checkUserType(User user){
        if (user.isAdmin())
            return UserRoles.ADMIN;
        else
            return UserRoles.USER;
    }
}
