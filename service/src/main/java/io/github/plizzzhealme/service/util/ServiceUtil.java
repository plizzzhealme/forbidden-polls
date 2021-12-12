package io.github.plizzzhealme.service.util;

public final class ServiceUtil {

    private ServiceUtil() {
    }

    public static int passwordToHash(String password) {
        if (password == null) {
            return 0;
        }

        return password.hashCode();
    }
}
