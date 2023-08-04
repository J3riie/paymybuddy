package com.paymybuddy.paymybuddy.user.repository;

import java.util.Optional;

import com.paymybuddy.paymybuddy.user.model.User;

public interface UserRepository {

    Optional<User> checkIfAccountExists(String email);

    void saveUser(User user);
}