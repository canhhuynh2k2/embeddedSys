package vn.edu.ptit.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.quiz.entity.Answer;
import vn.edu.ptit.quiz.exception.NotFoundException;
import vn.edu.ptit.quiz.model.AnswerDto;
import vn.edu.ptit.quiz.repository.AnswerRepo;
import vn.edu.ptit.quiz.service.AnswerService;
import vn.edu.ptit.quiz.util.converter.ConverterUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepo answerRepo;
    @Override
    public List<AnswerDto> getAnswerByQuestionId(Long questionId) {
        Set<Answer> answers = answerRepo.findByQuestionId(questionId);
        if(answers != null){
            return ConverterUtil.mapList(Arrays.asList(answers.toArray()), AnswerDto.class);
        }
        throw new NotFoundException("List answers not found by questionId " + questionId);
    }
}
