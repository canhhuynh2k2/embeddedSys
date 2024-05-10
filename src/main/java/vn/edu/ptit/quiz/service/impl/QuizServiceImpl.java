package vn.edu.ptit.quiz.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.quiz.config.AppConstant;
import vn.edu.ptit.quiz.entity.Answer;
import vn.edu.ptit.quiz.entity.Question;
import vn.edu.ptit.quiz.entity.Quiz;
import vn.edu.ptit.quiz.exception.NotFoundException;
import vn.edu.ptit.quiz.exception.UnAuthorizedException;
import vn.edu.ptit.quiz.model.*;
import vn.edu.ptit.quiz.repository.AnswerRepo;
import vn.edu.ptit.quiz.repository.QuestionRepo;
import vn.edu.ptit.quiz.repository.QuizRepo;
import vn.edu.ptit.quiz.service.QuizService;
import vn.edu.ptit.quiz.util.converter.ConverterUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepo quizRepo;

    private final QuestionRepo questionRepo;

    private final AnswerRepo answerRepo;
    @Override
    @Transactional
    public QuizDto createQuiz(QuizAM quizAM) {
        Quiz quiz = ConverterUtil.mappingToObject(quizAM, Quiz.class);
        quizRepo.save(quiz);
        for(Question question : quiz.getQuestions()){
            question.setQuiz(quiz);
            questionRepo.save(question);
            for(Answer answer : question.getListAnswers()){
                answer.setQuestion(question);
                answerRepo.save(answer);
            }
            question.setListAnswers(question.getListAnswers());
        }
        quiz.setQuestions(quiz.getQuestions());
        quiz.setStatus(AppConstant.QUIZ.STATUS.NOT_ACTIVE);

        return ConverterUtil.mappingToObject(quiz, QuizDto.class);
    }


    @Override
    public QuizDto getQuizById(Integer quizId) {
        Optional<Quiz> quiz = Optional.ofNullable(quizRepo.findById(quizId)
                .orElseThrow(() -> new NotFoundException("Quiz not found by id " + quizId)));
        return ConverterUtil.mappingToObject(quiz, QuizDto.class);
    }


//    @Override
//    public QuizDto startQuiz(Integer quizId, StartQuizRequest startQuizRequest) {
//        Quiz quiz = quizRepo.findById(quizId)
//                .orElseThrow(() -> new UnAuthorizedException("UnAuthorized"));
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        try {
//            Date startTime = dateFormat.parse(startQuizRequest.getStartAt());
//            Date endTime = dateFormat.parse(startQuizRequest.getEndAt());
//            quiz.setStartAt(new Timestamp(startTime.getTime()));
//            quiz.setEndAt(new Timestamp(endTime.getTime()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//
//        }
//
//        quiz.setStatus(AppConstant.QUIZ.STATUS.ACTIVE);
//        quizRepo.save(quiz);
//        return ConverterUtil.mappingToObject(quiz, QuizDto.class);
//    }
//
//    @Override
//    public QuizDto stopQuiz(Integer quizId) {
//        Quiz quiz = quizRepo.findById(quizId)
//                .orElseThrow(() -> new UnAuthorizedException("UnAuthorized"));
//        quiz.setStartAt(null);
//        quiz.setStatus(AppConstant.QUIZ.STATUS.NOT_ACTIVE);
//        quizRepo.save(quiz);
//        return ConverterUtil.mappingToObject(quiz, QuizDto.class);
//    }

}
