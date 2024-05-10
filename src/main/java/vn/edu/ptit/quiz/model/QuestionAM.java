package vn.edu.ptit.quiz.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionAM {
    private String question;
    private Integer level;
    private Integer maxTime;
    private Integer point;
    private List<AnswerAM> listAnswers;
}
