package io.github.plizzzhealme.service.validator;

import io.github.plizzzhealme.bean.User;
import io.github.plizzzhealme.service.exception.ValidatorException;
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

    public void validateUser(User user) throws ValidatorException {
        if (user == null) {
            throw new ValidatorException("Invalid user data");
        }

        if (!isValidEmail(user.getEmail())) {
            throw new ValidatorException("Invalid email");
        }

        if (!isValidPassword(user.getPassword())) {
            throw new ValidatorException("Invalid password");
        }

        if (!isValidGender(user.getGender())) {
            throw new ValidatorException("Invalid gender");
        }

        if (!isValidBirthday(user.getBirthday())) {
            throw new ValidatorException("Invalid birthday");
        }

        if (!isValidName(user.getName())) {
            throw new ValidatorException("Invalid name");
        }

        if (!isValidCountyCode(user.getCountry())) {
            throw new ValidatorException("Invalid country");
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
        return StringUtils.equalsAnyIgnoreCase(gender, User.MALE_GENDER, User.FEMALE_GENDER, User.OTHER_GENDER);
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
