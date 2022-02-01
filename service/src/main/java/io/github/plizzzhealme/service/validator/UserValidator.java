package io.github.plizzzhealme.service.validator;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.service.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.util.Locale;
import java.util.regex.Pattern;

public class UserValidator {

    private static final UserValidator INSTANCE = new UserValidator();

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final String[] COUNTRIES = Locale.getISOCountries();

    public static UserValidator getInstance() {
        return INSTANCE;
    }

    public void validateUser(User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("Invalid user data");
        }

        if (!isValidEmail(user.getEmail())) {
            throw new ValidationException("Invalid email");
        }

        if (!isValidPassword(user.getPassword())) {
            throw new ValidationException("Invalid password");
        }

        if (!isValidGender(user.getGender())) {
            throw new ValidationException("Invalid gender");
        }

        if (!isValidBirthday(user.getBirthday())) {
            throw new ValidationException("Invalid birthday");
        }

        if (!isValidName(user.getName())) {
            throw new ValidationException("Invalid name");
        }

        if (!isValidCountyCode(user.getCountry())) {
            throw new ValidationException("Invalid country");
        }
    }

    public boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public boolean isValidPassword(String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }

        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public boolean isValidGender(String gender) {
        return StringUtils.equalsAnyIgnoreCase(gender, User.MALE, User.FEMALE, User.OTHER);
    }

    public boolean isValidBirthday(LocalDate birthday) {
        return birthday != null;
    }

    public boolean isValidName(String name) {
        return StringUtils.isNotBlank(name);
    }

    public boolean isValidCountyCode(String iso2) {
        return StringUtils.equalsAny(iso2, COUNTRIES);
    }
}
