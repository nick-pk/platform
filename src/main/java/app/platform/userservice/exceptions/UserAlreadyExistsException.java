package app.platform.userservice.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(){
        super("User already exists");
    }
}
