package vn.edu.ptit.quiz.service;

import vn.edu.ptit.quiz.model.*;

import java.util.List;
import java.util.Set;

public interface QuizService {
    QuizDto createQuiz(Long categoryId, QuizAM quizAM);

    QuizDto getQuizById(Integer quizId);
    List<QuizNameDto> getAllQuizByCategory(Integer categoryId);
//    QuizDto startQuiz(Integer quizId, StartQuizRequest startQuizRequest);
//
//    QuizDto stopQuiz(Integer quizId);
}
