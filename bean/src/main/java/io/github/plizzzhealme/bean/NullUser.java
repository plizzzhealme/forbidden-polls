package io.github.plizzzhealme.bean;

import java.io.Serial;

public class NullUser extends User {

    @Serial
    private static final long serialVersionUID = 6820420156900461012L;

    private static final NullUser INSTANCE = new NullUser();

    private NullUser() {
    }

    public static NullUser getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
