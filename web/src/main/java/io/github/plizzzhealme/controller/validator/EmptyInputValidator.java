package io.github.plizzzhealme.controller.validator;

import io.github.plizzzhealme.controller.exception.EmptyInputException;
import org.apache.commons.lang3.StringUtils;

public class EmptyInputValidator {

    private static final EmptyInputValidator INSTANCE = new EmptyInputValidator();

    private EmptyInputValidator() {
    }

    public static EmptyInputValidator getInstance() {
        return INSTANCE;
    }

    public void validateEmptyInput(String... params) throws EmptyInputException {
        if (StringUtils.isAnyBlank(params)) {
            throw new EmptyInputException("empty fields");
        }
    }
}
