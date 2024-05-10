package vn.edu.ptit.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartQuizRequest {
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String startAt;

//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String endAt;
}
