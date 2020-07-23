package app.platform.userservice.services;

import app.platform.userservice.dtos.PasswordResetDto;
import app.platform.userservice.dtos.UserDto;
import app.platform.userservice.events.SuccessfulRegistrationEvent;
import app.platform.userservice.exceptions.TokenExpiredException;
import app.platform.userservice.exceptions.UserAlreadyExistsException;
import app.platform.userservice.exceptions.UserNotFoundException;
import app.platform.userservice.models.User;
import app.platform.userservice.models.VerificationToken;
import app.platform.userservice.repostiories.UserRepository;
import app.platform.userservice.repostiories.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private PasswordEncoder passwordEncoder;
    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public User registerUser(UserDto userDto) {
        User persistentUser=userRepository.findByEmail(userDto.getEmail());
        if(persistentUser!=null){
            throw new UserAlreadyExistsException();
        }
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User savedUser=userRepository.save(user);
        applicationEventPublisher.publishEvent(
                new SuccessfulRegistrationEvent(savedUser)
        );
        return savedUser;
    }

    @Override
    public User validateUser(String token) {
        VerificationToken verificationToken=verificationTokenRepository.findByToken(token);
        if(verificationToken==null
                || verificationToken.getExpiryTime().getTime()-new Date().getTime()<0){
            throw new TokenExpiredException();
        }
            User verifiedUser=verificationToken.getUser();
            verifiedUser.setActive(true);
            userRepository.save(verifiedUser);
            verificationTokenRepository.delete(verificationToken);
            return verifiedUser;

    }

    @Override
    public String forgotPassword(String email) {
        User persistentUser=userRepository.findByEmail(email);
        if(persistentUser==null){
            throw new UserNotFoundException();
        }
        VerificationToken token=new VerificationToken(persistentUser);
        verificationTokenRepository.save(token);
        //TODO: Send email with token

        return token.getToken();//"Please check email for password reset!";

    }

    @Override
    public String resetPassword(PasswordResetDto passwordResetDto) {
        User user=validateUser(passwordResetDto.getToken());
        if(user==null){
            throw new TokenExpiredException();
        }
        System.out.println(passwordResetDto);
        user.setPassword(passwordEncoder.encode(passwordResetDto.getNewPassword()));
        userRepository.save(user);
        return "Password reset successfully";
    }
}
