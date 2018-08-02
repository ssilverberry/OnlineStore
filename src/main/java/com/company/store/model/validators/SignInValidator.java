package com.company.store.model.validators;


import com.company.store.model.entities.User;
import com.company.store.model.repository.impl.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Collection;

@Controller
public class SignInValidator implements Validator {

    private UserDAOImpl dataBase;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Autowired
    public SignInValidator(UserDAOImpl dataBase) {
        this.dataBase = dataBase;
    }
    
    @Override
    public void validate(Object object, Errors errors) {
        boolean flag = false;
        User user = (User) object;
        Collection<User> userCol = dataBase.getAllUsers();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty.login");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty.login");

        for (User x : userCol) {
            if (x.getEmail().equals(user.getEmail())) {
                flag = true;
                if (!x.getPassword().equals(user.getPassword()) && x.getEmail().equals(user.getEmail()))
                    errors.rejectValue("password", "auth.failed.wrongCredentials");
            }
        }
        if (!flag)
            errors.rejectValue("email", "auth.failed.noSuchEmail");
    }
}
