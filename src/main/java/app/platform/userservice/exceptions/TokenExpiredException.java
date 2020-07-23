package app.platform.userservice.exceptions;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(){
        super("Token Expired");
    }
}
