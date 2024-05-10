package vn.edu.ptit.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.quiz.entity.Quiz;

import java.util.Optional;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {
//    Optional<Quiz> findById(Integer id);
//    @Query("SELECT q FROM Quiz q WHERE q.token = :quizToken")
//    Optional<Quiz> findByTokenAndStudentId(String quizToken, Integer studentId);

//    @Query("SELECT q FROM Quiz q JOIN FETCH q.students s WHERE q.id = :id")
//    Optional<Quiz> findByQuizId(Integer id);
}
