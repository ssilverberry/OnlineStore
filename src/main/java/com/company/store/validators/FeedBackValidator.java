package com.company.store.validators;

import com.company.store.entities.Feedback;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Controller
public class FeedBackValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Feedback.class.equals(aClass);
    }
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "content", "content.empty");
    }
}
