package io.github.plizzzhealme.controller.exception;

import java.io.Serial;

public class EmptyInputException extends Exception {

    @Serial
    private static final long serialVersionUID = -2662560416688241643L;

    public EmptyInputException() {
        super();
    }

    public EmptyInputException(String message) {
        super(message);
    }

    public EmptyInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyInputException(Throwable cause) {
        super(cause);
    }
}
