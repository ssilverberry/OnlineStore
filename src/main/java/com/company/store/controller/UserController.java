package com.company.store.controller;

import com.company.store.model.entities.User;
import com.company.store.model.impls.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class UserController {
    @Autowired
    private UserDAOImpl userDAO;

    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "saveUser")
    public ModelAndView saveUser(User user) {
        userDAO.saveUser(user);
        return new ModelAndView("saveUser");
    }

    @RequestMapping(value = "allusers")
    public ModelAndView getAllUsers() {
        Collection<User> users = userDAO.getAllUsers();
        return new ModelAndView("allusers", "users", users);

    }

    @RequestMapping(value = "getById/{id}")
    public ModelAndView getById(@PathVariable("id") String id) {
        User user = userDAO.getById(Integer.parseInt(id));
        return new ModelAndView("getById", "user", user);
    }

    @RequestMapping(value = "getByCredential")
    public ModelAndView getByCredential(String email, String password) {
        User user = userDAO.getByCredentials(email, password);
        return new ModelAndView("getByCredential", "user", user);
    }

    @RequestMapping(value = "removeUser/{id}")
    public ModelAndView removeUser(@PathVariable("id") String id) {
        userDAO.removeUser(Integer.parseInt(id));
        return new ModelAndView("removeUser");
    }
}
