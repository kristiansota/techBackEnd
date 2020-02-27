package com.kristian.test.validaotrs;

import com.kristian.test.annotations.UniqueEmail;
import com.kristian.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (userRepository == null) {
            return true;
        }
        return (userRepository.findByEmail(email) == null);
    }
}
