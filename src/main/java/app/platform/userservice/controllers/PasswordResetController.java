package app.platform.userservice.controllers;
import app.platform.userservice.dtos.PasswordResetDto;
import app.platform.userservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class PasswordResetController {
    private UserService userService;
    @GetMapping("/forgotPassword")
    public String forgotPassword(@RequestParam String email){
        return userService.forgotPassword(email);

    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody @Valid PasswordResetDto passwordResetDto){
        return userService.resetPassword(passwordResetDto);
    }
}
