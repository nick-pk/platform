package app.platform.userservice.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){super("User not found");}
}
