package vn.edu.ptit.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.quiz.entity.Question;
import vn.edu.ptit.quiz.exception.NotFoundException;
import vn.edu.ptit.quiz.exception.UnAuthorizedException;
import vn.edu.ptit.quiz.model.QuestionDto;
import vn.edu.ptit.quiz.repository.QuestionRepo;
import vn.edu.ptit.quiz.service.QuestionService;
import vn.edu.ptit.quiz.util.converter.ConverterUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;
    @Override
    public QuestionDto getQuestionById(Long id) {
        Optional<Question> question = Optional.ofNullable(questionRepo.findByQuestionId(id)
                .orElseThrow(() -> new NotFoundException("question id " + id)));

        return ConverterUtil.mappingToObject(question, QuestionDto.class);
    }

//    @Override
//    public QuestionAnswerDto getAnswersByQuestionId(Long questionId) {
//        Optional<Question> question = Optional.ofNullable(questionRepo.findByQuestionId(questionId)
//                .orElseThrow(() -> new NotFoundException("question id " + questionId)));
//        return ConverterUtil.mappingToObject(question, QuestionAnswerDto.class);
//    }

    public QuestionDto getQuestion(Integer quizId, Integer questionId){
//        Quiz quiz = quizRepo.findById(quizId)
//                .orElseThrow(() -> new NotFoundException("Quiz id " + quizId));

        Question question = questionRepo.getQuestion(quizId, questionId);
        if(question != null){
            return ConverterUtil.mappingToObject(question, QuestionDto.class);
        }
        throw new NotFoundException("Quiz id " + quizId + " or question id " + questionId + " not found");
    }
}
