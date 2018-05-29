package com.company.store.controller;

import com.company.store.model.entities.User;
import com.company.store.model.impls.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView saveUser(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "surname") String surname,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "phone") String phone,
                                 @RequestParam(value = "password") String password,
                                 @RequestParam(value = "address") String address) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setAddress(address);
        Boolean add = userDAO.saveUser(user);
        if(add==true){
        return new ModelAndView("saveUser");
        }else
            return new ModelAndView("noUserSave");
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

    @RequestMapping(value = "getByCredential")
    public ModelAndView getByCredential(@RequestParam(value = "email") String email,
                                        @RequestParam(value = "password") String password) {
        User user = userDAO.getByCredentials(email, password);
        return new ModelAndView("getByCredential", "user", user);
    }

    @RequestMapping(value = "removeUser")
    public ModelAndView removeUser(@RequestParam(value = "id") int id) {
       Boolean up = userDAO.removeUser(id);
       /*up ? return new ModelAndView("removeUser") :
        return new ModelAndView("user")*/
        if(up == true){
            return new ModelAndView("removeUser");
        }
        else
        return new ModelAndView("user");
    }
}
