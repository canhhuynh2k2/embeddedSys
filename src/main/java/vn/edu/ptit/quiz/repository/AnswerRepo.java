package vn.edu.ptit.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.quiz.entity.Answer;

import java.util.Set;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {
    @Query(value = "SELECT a FROM Answer a")
    Set<Answer> findByQuestionId(Long questionId);
}
