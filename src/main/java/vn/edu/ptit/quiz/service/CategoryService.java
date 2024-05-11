package vn.edu.ptit.quiz.service;

import vn.edu.ptit.quiz.model.CategoryAM;
import vn.edu.ptit.quiz.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto createCategory(CategoryAM categoryAM);
    CategoryDto updateCategory(Long id, CategoryAM categoryAM);
    void deleteCategory(Long id);
}
