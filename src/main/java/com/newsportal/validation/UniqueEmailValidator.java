package com.newsportal.validation;

import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext ctx) {
        if (userService == null) {
            return true;
        }
        return !userService.existsByEmail(emailField);
    }
}
