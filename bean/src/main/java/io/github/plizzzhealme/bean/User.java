package io.github.plizzzhealme.bean;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 6985791175479557902L;

    private int id;
    private String name;
    private String email;
    private LocalDateTime registrationDate;
    private LocalDate birthday;
    private String gender;
    private String userRole;
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof User user)) {
            return false;
        }

        return getId() == user.getId()
                && Objects.equals(getName(), user.getName())
                && Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getRegistrationDate(), user.getRegistrationDate())
                && Objects.equals(getBirthday(), user.getBirthday())
                && Objects.equals(getGender(), user.getGender())
                && Objects.equals(getUserRole(), user.getUserRole())
                && Objects.equals(getCountry(), user.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getName(),
                getEmail(),
                getRegistrationDate(),
                getBirthday(),
                getGender(),
                getUserRole(),
                getCountry());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", userRole='" + userRole + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
