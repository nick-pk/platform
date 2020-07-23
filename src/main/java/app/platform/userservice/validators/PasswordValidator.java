package app.platform.userservice.validators;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint,String> {
    @Value("${password.regex}")
    private String pattern;
    @Override
    public void initialize(PasswordConstraint password) {

    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext cxt) {
        return passwordField!=null && passwordField.matches(pattern);
    }
}
