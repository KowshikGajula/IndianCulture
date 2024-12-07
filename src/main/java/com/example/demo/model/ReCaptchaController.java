package com.example.demo.model;


//src/main/java/com/example/demo/controller/ReCaptchaController.java

import com.example.demo.model.*;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recaptcha")
public class ReCaptchaController {

 @Autowired
 private ReCaptchaService reCaptchaService;

 @PostMapping("/validate")
 public ResponseEntity<?> validateCaptcha(@RequestBody Map<String, String> request) {
     String token = request.get("token");
     if (token == null || token.isEmpty()) {
         return ResponseEntity.badRequest().body("reCAPTCHA token is missing");
     }

     boolean isValid = reCaptchaService.validateCaptcha(token);
     if (isValid) {
         return ResponseEntity.ok("Captcha verified successfully");
     } else {
         return ResponseEntity.status(400).body("Captcha verification failed");
     }
 }
}
