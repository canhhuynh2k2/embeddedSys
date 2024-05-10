package vn.edu.ptit.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizTest {
    private Integer id;

    private String title;

    private Timestamp startAt;

    private Integer timeLimit;

    private String description;

    List<Long> questionIds;

    private String studentToken;
}
