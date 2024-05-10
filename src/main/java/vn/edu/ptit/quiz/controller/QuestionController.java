package vn.edu.ptit.quiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.quiz.config.AppConstant;
import vn.edu.ptit.quiz.exception.UnAuthorizedException;
import vn.edu.ptit.quiz.service.QuestionService;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
@CrossOrigin
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{quizId}/{questionId}")
    public ResponseEntity<?> getQuestionById(@PathVariable("questionId") Integer questionId,
                                             @PathVariable("quizId") Integer quizId){
//        if(secretKey.equals(AppConstant.ANSWER.SECRET_KEY)){
            return ResponseEntity.ok(questionService.getQuestion(quizId, questionId));
//        }
//        throw new UnAuthorizedException("Unauthorized");
    }

//    @GetMapping("/{questionId}/answers")
//    public ResponseEntity<?> getAnswersByQuestionId(@RequestHeader("secret-key") String secretKey,
//                                                    @PathVariable("questionId") Long questionId){
//        if(secretKey.equals(AppConstant.ANSWER.SECRET_KEY)){
//            return ResponseEntity.ok(questionService.getAnswersByQuestionId(questionId));
//        }
//        throw new UnAuthorizedException("Unauthorized");
//    }
}
