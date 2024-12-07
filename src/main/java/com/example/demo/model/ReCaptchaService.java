package com.example.demo.model;

//src/main/java/com/example/demo/service/ReCaptchaService.java


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Service
public class ReCaptchaService {

 @Value("${recaptcha.secret-key}")
 private String secretKey;

 private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

 private final RestTemplate restTemplate = new RestTemplate();

 public boolean validateCaptcha(String token) {
     String url = String.format("%s?secret=%s&response=%s", VERIFY_URL, secretKey, token);
     ResponseEntity<Map> response = restTemplate.postForEntity(url, null, Map.class);

     if (response.getBody() != null) {
         return (boolean) response.getBody().get("success");
     }
     return false;
 }
}
