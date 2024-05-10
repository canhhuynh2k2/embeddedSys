package vn.edu.ptit.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Integer id;

    private String title;

    private Timestamp startAt;

    private Timestamp endAt;

    private String description;

    private String token;

    private Integer status;

    private List<QuestionDto> questions;
}
