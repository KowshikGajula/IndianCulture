package com.example.demo.model;

import com.example.demo.model.QuizResult;
import com.example.demo.model.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizResultService {

    @Autowired
    private QuizResultRepository quizResultRepository;

    public QuizResult saveResult(QuizResult result) {
        return quizResultRepository.save(result);
    }

    public List<QuizResult> getResultsByUserId(Long userId) {
        return quizResultRepository.findByUserId(userId);
    }
}
