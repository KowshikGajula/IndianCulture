package com.example.demo.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful!",
                    "role", loggedInUser.getRole(),
                    "userId", loggedInUser.getId()
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }
    }

    // Admin-specific: Retrieve all users
    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam String username, @RequestParam String password) {
        User user = userService.loginUser(username, password);
        if (user != null && userService.isAdmin(user)) {
            return ResponseEntity.ok(userService.getAllUsers());
        }
        return ResponseEntity.status(403).body(null); // Forbidden if not an admin
    }

    // Admin-specific: Update a user
    @PutMapping("/admin/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser, @RequestParam String username, @RequestParam String password) {
        User user = userService.loginUser(username, password);
        if (user != null && userService.isAdmin(user)) {
            return ResponseEntity.ok(userService.updateUser(id, updatedUser));
        }
        return ResponseEntity.status(403).body("Unauthorized action");
    }

    // Admin-specific: Delete a user
    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, @RequestParam String username, @RequestParam String password) {
        User user = userService.loginUser(username, password);
        if (user != null && userService.isAdmin(user)) {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.status(403).body("Unauthorized action");
    }

    // Endpoint to get user details by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
            .map(user -> ResponseEntity.ok(user))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}