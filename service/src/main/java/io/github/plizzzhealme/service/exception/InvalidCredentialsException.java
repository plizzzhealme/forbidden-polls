package io.github.plizzzhealme.service.exception;

import java.io.Serial;

public class InvalidCredentialsException extends Exception {

    @Serial
    private static final long serialVersionUID = 7078959142613198431L;

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
