package com.paymybuddy.paymybuddy.user.ui;

import jakarta.validation.constraints.Email;

public class UserRegistrationForm {

    @Email
    private String email;

    private String password;

    private String passwordConfirmation;

    public UserRegistrationForm(String password, String passwordConfirmation) {
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

}