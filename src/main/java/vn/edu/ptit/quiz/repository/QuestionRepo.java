package vn.edu.ptit.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.quiz.entity.Question;

import java.util.Optional;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q JOIN FETCH q.listAnswers WHERE q.id = ?1")
    Optional<Question> findByQuestionId(Long id);

    @Query("SELECT q FROM Question q JOIN FETCH q.listAnswers JOIN FETCH q.quiz quiz WHERE q.id = ?2 AND quiz.id = ?1")
    Question getQuestion(Integer quizId, Integer questionId);
}
