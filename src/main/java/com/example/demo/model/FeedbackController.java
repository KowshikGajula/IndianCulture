package com.example.demo.model;

import com.example.demo.model.User;
import com.example.demo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/feedback")
    public ResponseEntity<String> submitFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        if (feedbackRequest.getUserId() == null || feedbackRequest.getFeedback() == null) {
            return ResponseEntity.badRequest().body("User ID and feedback are required.");
        }

        // Fetch user details using userId
        User user = userRepository.findById(feedbackRequest.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        try {
            // Send email to admin with user's details
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("kowshikgajula1@gmail.com"); // Admin email
            message.setSubject("New Feedback Submission");
            message.setText("User ID: " + user.getId() + 
                            "\nUsername: " + user.getUsername() + 
                            "\nEmail: " + user.getEmail() +
                            "\nFeedback: " + feedbackRequest.getFeedback());
            mailSender.send(message);

            return ResponseEntity.ok("Feedback submitted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit feedback.");
        }
    }

    // Inner class for the request body structure
    public static class FeedbackRequest {
        private Long userId;
        private String feedback;

        // Getters and setters
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }
    }
}
