package com.company.store.model.services;

import com.company.store.model.repository.UserDAO;
import com.company.store.model.entities.User;
import com.company.store.model.entities.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
