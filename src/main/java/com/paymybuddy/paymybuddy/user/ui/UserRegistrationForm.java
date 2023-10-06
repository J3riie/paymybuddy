package com.paymybuddy.paymybuddy.user.ui;

import com.paymybuddy.paymybuddy.config.annotation.PasswordMatches;
import com.paymybuddy.paymybuddy.config.annotation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordMatches
public class UserRegistrationForm {

    @ValidEmail
    @NotBlank
    @Size(min = 15, max = 50, message = "Veuillez saisir un mail valide")
    private String email;

    @NotBlank(message = "Password must not be empty")
    private String password;

    @NotBlank(message = "Password must not be empty")
    private String passwordConfirmation;

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
