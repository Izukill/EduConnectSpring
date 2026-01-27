package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OperationException extends EduConnectException {

    public OperationException(String message) {
        super(message);
    }



}
