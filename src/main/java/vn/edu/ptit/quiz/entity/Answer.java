package vn.edu.ptit.quiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ANSWER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "IS_CORRECT")
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
}
