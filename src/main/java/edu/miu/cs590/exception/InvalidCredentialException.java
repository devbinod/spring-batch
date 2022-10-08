package edu.miu.cs590.exception;

public class InvalidCredentialException extends RuntimeException{

    public InvalidCredentialException(String message) {
        super(message);
    }
}
