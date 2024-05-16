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

    @PostMapping("/{categoryId}")
    public ResponseEntity<QuizDto> createQuiz( @PathVariable("categoryId") Long categoryId,
                                                @RequestBody QuizAM quizAM){
        QuizDto res = quizService.createQuiz(categoryId, quizAM);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<?> getAllQuizById(@PathVariable("quizId") Integer quizId){
        return ResponseEntity.ok(quizService.getQuizById(quizId));
    }

    @GetMapping("/all/{categoryId}")
    public ResponseEntity<?> getAllQuizByCategory(@PathVariable("categoryId") Integer categoryId){
        return ResponseEntity.ok(quizService.getAllQuizByCategory(categoryId));
    }

    @GetMapping("/generate/{categoryId}")
    public ResponseEntity<?> generateQuiz(@PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(quizService.generateQuiz(categoryId));
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
