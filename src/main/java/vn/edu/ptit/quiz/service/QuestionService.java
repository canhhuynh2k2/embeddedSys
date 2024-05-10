package vn.edu.ptit.quiz.service;

import vn.edu.ptit.quiz.model.QuestionAnswerDto;
import vn.edu.ptit.quiz.model.QuestionDto;

public interface QuestionService {
    QuestionDto getQuestionById(Long id);

//    QuestionAnswerDto getAnswersByQuestionId(Long questionId);

    QuestionDto getQuestion(Integer quizId, Integer questionId);
}
