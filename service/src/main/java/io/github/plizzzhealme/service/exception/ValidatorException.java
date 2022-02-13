package io.github.plizzzhealme.service.exception;

import java.io.Serial;

public class ValidatorException extends Exception {

    @Serial
    private static final long serialVersionUID = -8865901317327696978L;

    public ValidatorException(String message) {
        super(message);
    }
}
