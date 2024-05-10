package vn.edu.ptit.quiz.service;

import vn.edu.ptit.quiz.model.AnswerDto;

import java.util.List;

public interface AnswerService {
    List<AnswerDto> getAnswerByQuestionId(Long questionId);
}
