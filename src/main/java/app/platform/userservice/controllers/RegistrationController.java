package app.platform.userservice.controllers;

import app.platform.userservice.dtos.ResponseDto;
import app.platform.userservice.dtos.UserDto;
import app.platform.userservice.dtos.UserResponseDto;
import app.platform.userservice.models.User;
import app.platform.userservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class RegistrationController {
    private UserService userService;
    @PostMapping("/register")
    public ResponseDto<UserResponseDto> registerUser(@RequestBody @Valid UserDto userDto){
        User user=userService.registerUser(userDto);
        return new ResponseDto<>(HttpStatus.OK,
                new UserResponseDto(user.getId(),user.getFullName(),user.getEmail(),user.isActive()));
    }

    @GetMapping("/confirm")
    public ResponseDto<UserResponseDto> validateUser(@RequestParam String token){
        User user=userService.validateUser(token);
        return new ResponseDto<>(HttpStatus.OK,
                new UserResponseDto(user.getId(),user.getFullName(),user.getEmail(),user.isActive()));
    }
}
