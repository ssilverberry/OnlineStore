package com.company.store.controller;

import com.company.store.entities.User;
import com.company.store.services.UserService;
import com.company.store.validators.SignInValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@Controller
@SessionAttributes("user")
public class LogInController {

    private final UserService userService;

    private final SignInValidator signInValidator;

    @Autowired
    public LogInController(UserService userService, SignInValidator signInValidator) {
        this.userService = userService;
        this.signInValidator = signInValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(signInValidator);
    }

    @RequestMapping("/login")
    public String showLoginForm (Map<String, Object> model) {
        model.put("authForm", User.newBuilder().build());
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
            switch (userService.checkUserType(userService.getUser(user.getEmail(), user.getPassword()))) {
                case ADMIN:
                    model.put("user", userService.getUser(user.getEmail(), user.getPassword()));
                    return "admin";
                case USER:
                    model.put("user", userService.getUser(user.getEmail(), user.getPassword()));
                    return "index";
            }
        }
        return "authorize";
    }
}
