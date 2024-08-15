package com.example.exception;

public class DataReceiverException extends Exception {
    public DataReceiverException(String message) {
        super(message);
    }

    public DataReceiverException(String message, Throwable cause) {
        super(message, cause);
    }
}

