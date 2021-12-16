package io.github.plizzzhealme.service;

import io.github.plizzzhealme.bean.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    @Test
    void authorizeWithCorrectData() {
        String email = "plizzz.healme@gmail.com";
        String password = "1q2w3e";

        User expected;
        User actual;

        expected = new User();
        expected.setId(2);
        expected.setEmail(email);
        expected.setName("Dzianis");
        expected.setCountry("Belarus");
        expected.setGender("male");
        expected.setUserRole("admin");

        actual = new UserService().authorize(email, password);

        assertEquals(expected, actual);
    }

    @Test
    void register() {
    }
}