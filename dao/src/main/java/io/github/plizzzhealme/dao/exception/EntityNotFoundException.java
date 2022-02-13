package io.github.plizzzhealme.dao.exception;

import java.io.Serial;

public class EntityNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 3633605686330648334L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
