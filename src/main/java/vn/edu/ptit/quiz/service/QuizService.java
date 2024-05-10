package vn.edu.ptit.quiz.service;

import vn.edu.ptit.quiz.model.QuizAM;
import vn.edu.ptit.quiz.model.QuizDto;
import vn.edu.ptit.quiz.model.QuizTest;
import vn.edu.ptit.quiz.model.StartQuizRequest;

import java.util.Set;

public interface QuizService {
    QuizDto createQuiz(QuizAM quizAM);

    QuizDto getQuizById(Integer quizId);

//    QuizDto startQuiz(Integer quizId, StartQuizRequest startQuizRequest);
//
//    QuizDto stopQuiz(Integer quizId);
}
