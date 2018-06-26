package com.company.store.model.services;

import com.company.store.model.dao.UserDAO;
import com.company.store.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public String validateUserType(User user){
        if (user.getIsAdmin()){
            return "admin";
        } else {
            return "user";
        }
    }
}
