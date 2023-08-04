package com.paymybuddy.paymybuddy.user.ui;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.paymybuddy.user.service.UserRegistrationService;

import jakarta.validation.Valid;

@Controller
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping("/user")
    public String displayUserRegistrationForm(Model model) {
        final String autogeneratedPassword = UUID.randomUUID().toString();
        final UserRegistrationForm userRegistrationForm = new UserRegistrationForm(autogeneratedPassword, autogeneratedPassword);
        model.addAttribute("user", userRegistrationForm);
        return "user_registration";
    }

    @PostMapping("/user")
    public String handleUserForm(@Valid @ModelAttribute("user") UserRegistrationForm userRegistrationForm, Model model) {
        model.addAttribute("userEmail", userRegistrationForm.getEmail());
        userRegistrationService.createAccount(userRegistrationForm.getEmail(), userRegistrationForm.getPassword());
        return "user_registration_success";
    }

}