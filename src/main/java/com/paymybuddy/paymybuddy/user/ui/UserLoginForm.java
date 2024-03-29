package com.paymybuddy.paymybuddy.user.ui;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLoginForm {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public UserLoginForm() {
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

}
