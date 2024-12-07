package com.example.demo.model;

import com.example.demo.model.QuizResult;
import com.example.demo.model.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    
    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private QuizResultRepository quizResultRepository;

    public List<Quiz> getAllQuizzes() { return quizRepository.findAll(); }
    
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
    }

    public Quiz createQuiz(Quiz quiz) { return quizRepository.save(quiz); }
    
    public Quiz updateQuiz(Long id, Quiz updatedQuiz) { 
        Quiz quiz = getQuizById(id);
        quiz.setQuestion(updatedQuiz.getQuestion());
        quiz.setOptionA(updatedQuiz.getOptionA());
        quiz.setOptionB(updatedQuiz.getOptionB());
        quiz.setOptionC(updatedQuiz.getOptionC());
        quiz.setOptionD(updatedQuiz.getOptionD());
        quiz.setCorrectAnswer(updatedQuiz.getCorrectAnswer());
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) { quizRepository.deleteById(id); }
    
    // Save quiz result
    public QuizResult saveQuizResult(QuizResult result) {
        return quizResultRepository.save(result);
    }
    
    public List<QuizResult> getQuizResults(Long userId) {
        return quizResultRepository.findByUserId(userId);
    }
}
