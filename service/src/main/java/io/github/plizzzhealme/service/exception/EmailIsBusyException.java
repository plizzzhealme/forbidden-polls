package io.github.plizzzhealme.service.exception;

import java.io.Serial;

public class EmailIsBusyException extends Exception {

    @Serial
    private static final long serialVersionUID = -1968764497502390272L;

    public EmailIsBusyException() {
        super();
    }

    public EmailIsBusyException(String message) {
        super(message);
    }

    public EmailIsBusyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailIsBusyException(Throwable cause) {
        super(cause);
    }
}
