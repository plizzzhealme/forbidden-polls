package io.github.plizzzhealme.service.util;

public final class ServiceUtil {

    private ServiceUtil() {
    }

    // todo replace with b-crypt
    public static int passwordToHash(String password) {
        if (password == null) {
            return 0;
        }

        return password.hashCode();
    }
}
