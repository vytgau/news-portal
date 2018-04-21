package com.newsportal.validation;

import com.newsportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String usernameField, ConstraintValidatorContext ctx) {
        if (userService == null) {
            return true;
        }
        return !userService.existsByUsername(usernameField);
    }
}
