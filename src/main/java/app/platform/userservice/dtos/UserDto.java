package app.platform.userservice.dtos;

import app.platform.userservice.validators.EmailConstraint;
import app.platform.userservice.validators.PasswordConstraint;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserDto {
    @NotEmpty
    private String fullName;
    @EmailConstraint(message = "Please enter valid email Id!")
    @NotEmpty
    private String email;
    @PasswordConstraint(message = "Password must contain uppercase and lowercase letters,digits and symbol without whitespaces!")

    @NotEmpty
    private String password;
}
