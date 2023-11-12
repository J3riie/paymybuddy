package com.paymybuddy.paymybuddy.user.service;

import com.paymybuddy.paymybuddy.exception.FunctionalException;
import com.paymybuddy.paymybuddy.user.model.User;
import com.paymybuddy.paymybuddy.user.repository.UserRepository;
import com.paymybuddy.paymybuddy.user.ui.UserRegistrationForm;
import com.paymybuddy.paymybuddy.utils.MainLogger;
import com.paymybuddy.paymybuddy.utils.UserUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final MainLogger logger = MainLogger.getLogger(UserService.class);

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public User getUser(String email) {
        return userRepository.findByUsernameOrEmail(email).orElseThrow(() -> new FunctionalException(String.format("Email %s not exists", email)));
    }

    public void signUp(UserRegistrationForm registrationForm) {
        String email = registrationForm.getEmail();
        logger.info("Creating a new account for email {0}", email);
        String username = registrationForm.getUsername();
        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(email, username);
        if (optionalUser.isPresent()) {
            logger.error("{0} already is present in the database", email);
            throw new FunctionalException(String.format("User %s already exists", email));
        }
        logger.info("The email is new, saving the new user in the database");
        User user = new User(email, username, encoder.encode(registrationForm.getPassword()));
        userRepository.save(user);
        logger.info("User {0} successfully registered", email);
    }

    public void addConnection(String connectionEmail) {
        final String authenticatedUserEmail = UserUtil.getAuthenticatedUserEmail();
        logger.info("Adding {0} to {1} connections", connectionEmail,
                authenticatedUserEmail);
        logger.info("Checking if given connection email is present in the database");
        Optional<User> optionalConnection = userRepository.findByUsernameOrEmail(connectionEmail);
        if (optionalConnection.isEmpty()) {
            throw new FunctionalException(String.format("No account with this email %s exist", connectionEmail));
        }
        final User user = userRepository.getReferenceById(authenticatedUserEmail);
        final User connection = optionalConnection.get();
        user.addConnection(connection.getUsername());
        userRepository.save(user);
        logger.info("Friend {0} successsfully added", connection.getUsername());
    }

    public List<String> getConnections() {
        String authenticatedUserEmail = UserUtil.getAuthenticatedUserEmail();
        logger.info("get connection for user {0}", authenticatedUserEmail);
        User user = userRepository.getReferenceById(authenticatedUserEmail);
        return user.getConnections();
    }
}