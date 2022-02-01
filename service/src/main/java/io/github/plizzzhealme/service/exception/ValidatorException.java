package io.github.plizzzhealme.service.exception;

import java.io.Serial;

public class ValidatorException extends Exception {

    @Serial
    private static final long serialVersionUID = -8865901317327696978L;

    public ValidatorException() {
        super();
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
