package vn.edu.ptit.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizAM {
    private String title;
    private Integer timeLimit;
    private String description;
    private Integer status;
    private List<QuestionAM> questions;
}
