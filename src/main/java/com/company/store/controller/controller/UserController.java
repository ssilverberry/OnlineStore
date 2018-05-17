package com.company.store.controller.controller;


import com.company.store.model.entities.User;
import com.company.store.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("viewUser")
    public ModelAndView showAllUsers () {
        User user = userDAO.getById(1000000);
        return new ModelAndView("viewUser", "user", user);
    }
}
