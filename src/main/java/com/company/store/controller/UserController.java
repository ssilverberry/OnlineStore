package com.company.store.controller;

import com.company.store.model.entities.User;
import com.company.store.model.impls.UserDAOImpl;
import com.company.store.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class UserController {

    private UserDAOImpl userDAO;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping("/login")
    public String showLoginForm (Model model) {
        model.addAttribute("authForm", new User());
        return "authorize";
    }

    @RequestMapping("/userin")
    public ModelAndView loginAction(@RequestParam("email") String email,
                                    @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView("somePage");
        User user = new User(email, password);

        if (userService.isValid(user)){
            switch (userService.validateUser(user)){
                case "admin":
                    modelAndView = new ModelAndView("redirect:/admin/mainPage", "admin", user);
                    break;
                case "user":
                    modelAndView = new ModelAndView("redirect:/", "index", user);
                    break;
            }
            return modelAndView;
        } else{
            modelAndView.setViewName("errorPage");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new User());
        return "registration";
    }

    @RequestMapping(value = "addUser")
    public ModelAndView saveUser(Model model,
                                 @RequestParam("email") String email,
                                 @RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("password") String password,
                                 @RequestParam("address") String address) {
        User user = new User();
        model.addAttribute("registrationForm", user);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setAddress(address);
        Boolean add = userDAO.saveUser(user);
        if(add){
            return new ModelAndView("index", "user", user);
        }else {
            return new ModelAndView("index", "user", user);
        }
    }

    @RequestMapping(value = "allusers")
    public ModelAndView getAllUsers() {
        Collection<User> users = userDAO.getAllUsers();
        return new ModelAndView("allusers", "users", users);

    }

    @RequestMapping(value = "getById")
    public ModelAndView getById(@RequestParam(value = "id") int id) {
        User user = userDAO.getById(id);
        return new ModelAndView("getById", "user", user);
    }

    @RequestMapping(value = "removeUser")
    public ModelAndView removeUser(@RequestParam(value = "id") int id) {
       Boolean up = userDAO.removeUser(id);
        if(up){
            return new ModelAndView("removeUser");
        }
        else
        return new ModelAndView("user");
    }
}
