package app.platform.userservice.services;

import app.platform.userservice.dtos.PasswordResetDto;
import app.platform.userservice.dtos.UserDto;
import app.platform.userservice.models.User;

public interface UserService {
    User registerUser(UserDto userDto);
    User validateUser(String token);
    String forgotPassword(String email);
    String resetPassword(PasswordResetDto passwordResetDto);
}
