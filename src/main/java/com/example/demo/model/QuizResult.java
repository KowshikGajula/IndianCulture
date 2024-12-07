package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz_result")
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;  // or replace this with user identification if needed
    private int score;
    private int totalQuestions;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }

    public java.util.Date getDate() { return date; }
    public void setDate(java.util.Date date) { this.date = date; }
}
