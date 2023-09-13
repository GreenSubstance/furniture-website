package com.storeproject.demostore.services;

import com.storeproject.demostore.dto.request.UserDto;
import com.storeproject.demostore.models.Order;
import com.storeproject.demostore.models.Role;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.repos.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements UserDetailsService {
    final UserRepo userRepo;
    final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Transactional
    public void registerUser(UserDto user) {
        userRepo.save(User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .active(true)
                .role(Collections.singleton(Role.USER))
                .build());
    }

    @Transactional
    public String updateProfile(User user, UserDto userInfo) {

        if (userInfo.getPassword() != null && !userInfo.getPassword().isEmpty()) {
            if (Objects.equals(user.getPassword(), passwordEncoder.encode(userInfo.getPassword()))) return "New password cannot be the same as current password";
            else {
                user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
                userRepo.save(user);
            }
        }

        if (userInfo.getEmail() != null && !userInfo.getEmail().isEmpty()) {
            if (Objects.equals(userInfo.getEmail(), user.getUserEmail())) return "New email cannot be the same as current email";
            else {
                user.setUserEmail(userInfo.getEmail());
                userRepo.save(user);
            }
        }

        if (userInfo.getUsername() != null && !userInfo.getUsername().isEmpty()) {
            if (Objects.equals(user.getUsername(), userInfo.getUsername())) return "New username cannot be the same as current username";
            else {
                user.setUsername(userInfo.getUsername());
                userRepo.save(user);
            }
        }

        return "Successfully changed";
    }
}
