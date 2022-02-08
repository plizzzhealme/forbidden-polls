package io.github.plizzzhealme.service.exception;

import java.io.Serial;

public class EmailIsBusyException extends Exception {

    @Serial
    private static final long serialVersionUID = -1968764497502390272L;

    public EmailIsBusyException(String message) {
        super(message);
    }
}
