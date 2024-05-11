package vn.edu.ptit.quiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "QUIZ")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "quiz")
    private Set<Question> questions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;

}
