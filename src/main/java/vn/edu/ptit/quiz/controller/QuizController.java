package vn.edu.ptit.quiz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.quiz.exception.UnAuthorizedException;
import vn.edu.ptit.quiz.model.*;
import vn.edu.ptit.quiz.service.AdminService;
import vn.edu.ptit.quiz.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
@CrossOrigin
public class QuizController {

    private final QuizService quizService;

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<QuizDto> createQuiz( @RequestBody QuizAM quizAM){
        QuizDto res = quizService.createQuiz(quizAM);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<?> getAllQuizById(@PathVariable("quizId") Integer quizId){
        return ResponseEntity.ok(quizService.getQuizById(quizId));
    }


//    @PutMapping("/start/{quizId}")
//    public ResponseEntity<?> startQuiz(@RequestHeader("authentication") String authToken,
//                                       @PathVariable("quizId") Integer quizId,
//                                       @RequestBody StartQuizRequest startQuizRequest){
//        if(!adminService.authenticate(authToken))
//            throw new UnAuthorizedException("UnAuthorized");
//        return ResponseEntity.ok(quizService.startQuiz(quizId, startQuizRequest));
//    }
//
//    @PutMapping("/stop/{quizId}")
//    public ResponseEntity<?> stopQuiz(@RequestHeader("authentication") String authToken,
//                                       @PathVariable("quizId") Integer quizId){
//        if(!adminService.authenticate(authToken))
//            throw new UnAuthorizedException("UnAuthorized");
//        return ResponseEntity.ok(quizService.stopQuiz(quizId));
//    }
}
