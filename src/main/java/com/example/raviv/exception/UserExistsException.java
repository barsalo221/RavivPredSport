package com.example.raviv.exception;

public class UserExistsException extends RuntimeException{

    public UserExistsException(){
        super("userExists");
    }

}
