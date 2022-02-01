package io.github.plizzzhealme.service.exception;

import java.io.Serial;

public class ValidationException extends Exception {

    @Serial
    private static final long serialVersionUID = -8865901317327696978L;

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}
