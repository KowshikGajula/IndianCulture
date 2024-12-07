package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/admin/results")
public class QuizResultController {
    
    @Autowired
    private QuizResultService quizResultService; // Updated to the correct service name
    
    @PostMapping
    public ResponseEntity<QuizResult> saveResult(@RequestBody QuizResult quizResult) {
        return ResponseEntity.ok(quizResultService.saveResult(quizResult));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<QuizResult>> getUserResults(@PathVariable Long userId) {
        return ResponseEntity.ok(quizResultService.getResultsByUserId(userId));
    }
}
