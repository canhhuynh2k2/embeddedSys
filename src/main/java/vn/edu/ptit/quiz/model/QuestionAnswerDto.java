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
public class QuestionAnswerDto {
    private Long id;

    private String question;

    private Integer level;

    private Integer maxTime;

    private Integer point;

    private List<AnswerDto> listAnswers;
}
