package com.userregistration.exception;

public class UserListIsEmptyException extends RuntimeException{
    public UserListIsEmptyException(String message){
        super(message);
    }
}
