package com.epam.training.exception;

public class NoEntityException extends RuntimeException{
    public NoEntityException() {
    }

    public NoEntityException(String message) {
        super(message);
    }

    public NoEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEntityException(Throwable cause) {
        super(cause);
    }

}
