package co.kr.datapia.exceptions;

public class UserIdNotExistedException extends RuntimeException {
    public UserIdNotExistedException(String userId){
        super("Email is not registered " + userId);
    }
}