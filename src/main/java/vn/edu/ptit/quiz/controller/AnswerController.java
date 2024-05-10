package vn.edu.ptit.quiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.quiz.config.AppConstant;
import vn.edu.ptit.quiz.exception.UnAuthorizedException;
import vn.edu.ptit.quiz.service.AnswerService;

@RestController
@RequestMapping("/api/answer")
@RequiredArgsConstructor
@CrossOrigin
public class AnswerController {
    private final AnswerService answerService;

    @GetMapping("/{questionId}")
    public ResponseEntity<?> getAnswerByQuestionId(@PathVariable("questionId")Long questionId){
        System.out.println(AppConstant.ANSWER.SECRET_KEY);
//        if(!secretKey.equals(AppConstant.ANSWER.SECRET_KEY))
//            throw new UnAuthorizedException("UnAuthorized");
        return ResponseEntity.ok(answerService.getAnswerByQuestionId(questionId));
    }
}
