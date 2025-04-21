package com.blog.blog.services.impl;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.blog.models.User;
import com.blog.blog.repositories.UserRepository;
import com.blog.blog.services.UserService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
     @Autowired
     private PasswordEncoder passwordEncoder;

    @Override
    public User save(final User user) {
        User existingUser = userRepository.findByUsername(user.getName());
        if (Objects.nonNull(existingUser)) {
            throw new RuntimeException("Existing User");
        }
        // JsonEncoder passwordEncoder;
        String passwordHash = passwordEncoder.encode(user.getPassword());

        User entity = new User(user.getUserId(), user.getName(),user.getEmail(), passwordHash, user.getRole(), user.getUsername());
        User newUser = userRepository.save(entity);
        return new User(newUser.getUserId(), newUser.getName(),newUser.getEmail(), newUser.getPassword(), newUser.getRole(), user.getUsername());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User get(final Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("User not found")
        );
    }

    @Override
    public User update(Long id, User user) {
        User userUpdate = userRepository.findById(id).orElse(null);
        if (Objects.nonNull(userUpdate)) {
            String passwordHash = passwordEncoder.encode(user.getPassword());
            userUpdate.setPassword(passwordHash);
            userUpdate.setName(user.getName());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setRole(user.getRole());
            userUpdate.setUsername(user.getUsername());
            return userRepository.save(userUpdate);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
       userRepository.deleteById(id);
    }
    
}
