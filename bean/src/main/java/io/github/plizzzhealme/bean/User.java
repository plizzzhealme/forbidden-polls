package io.github.plizzzhealme.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class User implements Serializable {

    private int id;
    private String name;
    private String email;
    private int passwordHash; // not included into equals, hashcode, toString methods
    private LocalDateTime registrationDate;
    private long phoneNumber;
    private LocalDateTime lastLoginDate;
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

    public int getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
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
                && getPhoneNumber() == user.getPhoneNumber()
                && Objects.equals(getName(), user.getName())
                && Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getRegistrationDate(), user.getRegistrationDate())
                && Objects.equals(getLastLoginDate(), user.getLastLoginDate())
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
                getPhoneNumber(),
                getLastLoginDate(),
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
                ", phoneNumber=" + phoneNumber +
                ", lastLoginDate=" + lastLoginDate +
                ", gender='" + gender + '\'' +
                ", userRole='" + userRole + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
