package io.github.plizzzhealme.dao.exception;

import java.io.Serial;

public class DaoException extends Exception {

    @Serial
    private static final long serialVersionUID = -106783724452888681L;

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
