package vn.edu.ptit.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.quiz.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
