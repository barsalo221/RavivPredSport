package com.example.raviv.exception;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id){
        super("Could not found the user with id: " + id);
    }
    public UserNotFoundException(){
        super("Could not found the user" );
    }
}
