package app.platform.userservice.validators;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint,String> {
    @Value("${email.regex}")
    private String pattern;
    @Override
    public void initialize(EmailConstraint email) {

    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
        return emailField!=null && emailField.matches(pattern);
    }
}
