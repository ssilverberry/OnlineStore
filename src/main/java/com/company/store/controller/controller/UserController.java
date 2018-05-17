package com.company.store.controller.controller;

import com.company.store.controller.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @RequestMapping("viewResults")
    public ModelAndView showAllUsers () {
        User user = new User("Pasha");
        return new ModelAndView("viewUser", "user", user);
    }
}
