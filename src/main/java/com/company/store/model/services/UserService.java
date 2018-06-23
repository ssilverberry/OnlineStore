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

    public boolean isValid(User user){
        User tempUser = userDAO.getByCredentials(user.getEmail(), user.getPassword());
        return tempUser != null;
    }

    public String validateUser(User user){
        if (user.getIsAdmin()){
            return "admin";
        } else {
            return "user";
        }
    }
}
