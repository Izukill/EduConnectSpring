package org.example.exception;

import lombok.Getter;

@Getter
public class OperacaoException extends EduConnectException {

    private static final long serialVersionUID = 1L;

    public OperacaoException(String message) {
        super(message);
    }

    public OperacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}