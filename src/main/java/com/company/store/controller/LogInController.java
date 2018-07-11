package com.company.store.controller;

import com.company.store.model.entities.User;
import com.company.store.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class LogInController {

    private final UserService userService;

    @Autowired
    private SignInValidator signInValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(signInValidator);
    }

    @Autowired
    public LogInController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String showLoginForm (Map<String, Object> model) {
        model.put("authForm", new User());
        return "authorize";
    }

    @RequestMapping(value = "/userin", method = RequestMethod.POST)
    public String loginAction(@Valid @ModelAttribute("authForm") User user,
                                    BindingResult result,
                                    Map<String, Object> model,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password) {
        if (result.hasErrors()) {
            return "authorize";
        } else if (userService.getUser(user.getEmail(), user.getPassword()) != null) {
            switch (userService.validateUserType(userService.getUser(user.getEmail(), user.getPassword()))) {
                case "admin":
                    model.put("username", user.getName());
                    return "redirect:/admin/mainPage";
                case "user":
                    model.put("username", user.getName());
                    return "redirect:/";
            }
        }
        return "authorize";
    }
}
