package com.company.store.services.impl;

import com.company.store.repository.UserDAO;
import com.company.store.entities.User;
import com.company.store.entities.UserRoles;

import com.company.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUser(String email, String password){
        return userDAO.getByCredentials(email, password);
    }
    /**
     * Acceptable.
     * The method is used for matching needed user from Spring form with existing one in DB.
    */
    @Override
    public boolean match(String email, String password) {
        User user = userDAO.getByCredentials(email, password);
        if (user != null)
            return user.getEmail().equals(email) && user.getPassword().equals(password);
        return false;
    }

    @Override
    public Collection<User> getAll () {
        return userDAO.getAllUsers();
    }

    @Override
    public UserRoles checkUserType(User user){
        if (user.isAdmin())
            return UserRoles.ADMIN;
        else
            return UserRoles.USER;
    }
}
