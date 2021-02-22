package co.kr.datapia.security;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException(){
        super("Incorrect Password");
    }
}