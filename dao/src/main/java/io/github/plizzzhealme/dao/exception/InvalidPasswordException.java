package io.github.plizzzhealme.dao.exception;

import java.io.Serial;

public class InvalidPasswordException extends Exception {

    @Serial
    private static final long serialVersionUID = 7453735350785310297L;

    public InvalidPasswordException(String message) {
        super(message);
    }
}
