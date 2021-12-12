package io.github.plizzzhealme.service.util;

public class ServiceUtil {

    public int passwordToHash(String password) {
        if (password == null) {
            return 0;
        }

        return password.hashCode();
    }
}
