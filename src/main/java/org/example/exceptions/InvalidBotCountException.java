package org.example.exceptions;

public class InvalidBotCountException extends RuntimeException{

    public InvalidBotCountException(String message){
        super(message);
    }

//exception - checked
//runtime exception - unchecked
}
