package org.example.exception;

public class EduConnectException extends Exception {

    private static final long serialVersionUID = 1L;


    public EduConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public EduConnectException(String message) {
        super(message);
    }
}