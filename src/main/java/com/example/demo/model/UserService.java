package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Regular expression for strong password
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";

    // Method to check if the password matches the pattern
    public boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }

    // Register user method
    public User registerUser(User user) {
        // Validate the password before saving the user
        if (!isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Password must contain at least 6 characters, including uppercase, lowercase, numbers, and special characters.");
        }
        return userRepository.save(user);
    }
    @Transactional
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password.");
        }
        
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Password does not meet the security requirements.");
        }

        return user;
    }


    // Check if a user is an admin
    public boolean isAdmin(User user) {
        return "ADMIN".equals(user.getRole());
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update user details
    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(updatedUser.getEmail()	);
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        return userRepository.save(user);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

	public User validateToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}
}
