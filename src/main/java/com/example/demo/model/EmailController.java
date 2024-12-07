package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    // Endpoint to fetch all user emails
    @GetMapping("/users/emails")
    public ResponseEntity<List<String>> getAllUserEmails() {
        List<User> users = userRepository.findAll();
        List<String> emails = users.stream()
                                   .map(User::getEmail)
                                   .toList();
        return ResponseEntity.ok(emails);
    }

    // Endpoint to send email to all users
    @PostMapping("/send-email-to-all")
    public ResponseEntity<String> sendEmailToAll(@RequestBody EmailRequest emailRequest) {
        List<String> emails = emailRequest.getEmails(); // Get the list of emails
        String subject = emailRequest.getSubject();
        String message = emailRequest.getMessage();

        // Send email to each user
        for (String email : emails) {
            try {
                emailService.sendEmail(email, subject, message);
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error sending email: " + e.getMessage());
            }
        }
        return ResponseEntity.ok("Emails sent successfully.");
    }
}
