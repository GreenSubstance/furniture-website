package com.storeproject.demostore.services;

import com.storeproject.demostore.models.Order;
import com.storeproject.demostore.models.Role;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.repos.UserRepos;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class UserService implements UserDetailsService {
    final UserRepos userRepos;
    final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepos
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username)); // repos throws exception
    }

    public List<User> getAllUsers() {
        return userRepos.findAll();
    }

    @Transactional
    public void registerUser(User user) {
        User registeredUser = new User();
        registeredUser.setUsername(user.getUsername());
        registeredUser.setPassword(passwordEncoder.encode(user.getPassword()));
        registeredUser.setActive(true);
        registeredUser.setRoles(Collections.singleton(Role.USER));
        userRepos.save(registeredUser);
    }

    public User getCurrentlyLoggedInUser(Authentication authentication) {
        //if (authentication == null) return null;

        User user = null;
        Object principal = authentication.getPrincipal();

        user = (User) loadUserByUsername(((UserDetails) principal).getUsername());
        return user;
    }

    @Transactional
    public String updateProfile(User user, String username, String password, String email) {
        String oldPassword = user.getPassword();
        String oldEmail = user.getUser_email();

        if (password != null && !password.isEmpty()) {
            if (oldPassword == passwordEncoder.encode(password)) return "New password cannot be the same as current password";
            else {
                user.setPassword(passwordEncoder.encode(password));
                userRepos.save(user);
            }
        }

        if (email != null && !email.isEmpty()) {
            if (oldEmail == email) return "New email cannot be the same as current email";
            else {
                user.setUser_email(email);
                userRepos.save(user);
            }
        }

        if (username != null && !username.isEmpty()) {
            if (oldEmail == email) return "New username cannot be the same as current username";
            else {
                user.setUsername(username);
                userRepos.save(user);
            }
        }

        return "Successfully changed";
    }

    @Transactional
    public void saveUser(User user) {
        userRepos.save(user);
    }

    @Transactional
    public List<Order> getOrders(User user) {
        return userRepos
                .findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(user.getUsername()))
                .getOrders();
    }
}
