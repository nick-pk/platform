package app.platform.userservice.dtos;

import app.platform.userservice.validators.PasswordConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetDto {
    @PasswordConstraint
    private String newPassword;
    private String token;
}
